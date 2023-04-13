package com.dawncoody.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dawncoody.reggie.common.R;
import com.dawncoody.reggie.entity.User;
import com.dawncoody.reggie.service.MailService;
import com.dawncoody.reggie.service.UserService;
import com.dawncoody.reggie.util.ValidateCodeUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-24 19:46
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 发送邮箱验证码
     *
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        // 获取手机号
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)) {
            // 生成随机的4位验证码
            String code = String.valueOf(ValidateCodeUtils.generateValidateCode(4));
            // 调用邮箱服务发送验证码
            log.info("开始发送邮件");
            log.info("验证码{}", code);
            mailService.sendEmail("3050511039@qq.com", user.getPhone(), "瑞吉外卖验证码", "验证码为：[" + code + "]");

            // 将生成的验证码保存到Session中
            // session.setAttribute(phone, code);

            // 将生成的验证码缓存到Redis中，并且设置有效期为5分钟
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

            return R.success("邮箱验证码发送成功");
        }

        return R.error("邮箱发送失败");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map<String, Object> map, HttpSession session) {
        log.info(map.toString());
        // 获取手机号
        String mail = map.get("phone").toString();
        // 获取验证码
        String code = map.get("code").toString();

        // 从Session中获取保存的验证码
        // Object codeInSession = session.getAttribute(mail);

        // 从Redis中获取缓存的验证码
        Object codeInSession = redisTemplate.opsForValue().get(mail);

        // 进行验证码的对比（页面提交的验证码和Session中保存的验证码对比）
        if (codeInSession != null && codeInSession.equals(code)) {
            // 登录成功
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, mail);
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                // 判断当前手机号对应的用户是否为新用户，如果是新用户自动完成注册
                user = new User();
                user.setPhone(mail);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());
            return R.success(user);
        }
        // 如果用户登录成功，删除Redis中缓存的验证码
        redisTemplate.delete(mail);
        return R.error("登录失败");
    }

    @PostMapping("/loginout")
    public R<String> loginout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }
}
