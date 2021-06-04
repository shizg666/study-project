package cn.shizg.study.project.oauth.server.config;

import cn.shizg.study.project.oauth.server.security.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @ClassName OAuth2Config
 * @Description: TODO
 * @Author shizg
 * @Date 2021/6/3
 * @Version V1.0
 **/
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public AuthUserDetailsService authUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore redisTokenStore;
    //        @Autowired
//        private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        authenticationManage() 调用此方法才能支持 password 模式。
//        userDetailsService() 设置用户验证服务。
//        tokenStore() 指定 token 的存储方式。
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(authUserDetailsService)
                .tokenStore(redisTokenStore);
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //上面代码中是使用 inMemory 方式存储的，将配置保存到内存中，相当于硬编码了。正式环境下的做法是持久化到数据库中，比如 mysql 中。
//        JdbcClientDetailsServiceBuilder jcsb = clients.jdbc(dataSource);
//        jcsb.passwordEncoder(passwordEncoder);

        clients.inMemory()
                .withClient("order-client")
                .secret(passwordEncoder.encode("order-secret-8888"))
//                authorizedGrantTypes 可以包括如下几种设置中的一种或多种：
//                    authorization_code：授权码类型。
//                    implicit：隐式授权类型。
//                    password：资源所有者（即用户）密码类型。
//                    client_credentials：客户端凭据（客户端ID以及Key）类型。
//                    refresh_token：通过以上授权获得的刷新令牌来获取新的令牌。
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                //token有效期
                .accessTokenValiditySeconds(3600)

                //scopes：用来限制客户端访问的权限，在换取的 token 的时候会带上 scope 参数，只有在 scopes 定义内的，才可以正常换取 token。
                .scopes("all")
                .and()
                .withClient("user-client")
                .secret(passwordEncoder.encode("user-secret-8888"))
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                .accessTokenValiditySeconds(3600)
                .scopes("all");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许客户端访问 OAuth2 授权接口，否则请求 token 会返回 401。
        security.allowFormAuthenticationForClients();
        //第二行和第三行分别是允许已授权用户访问 checkToken 接口和获取 token 接口。
        security.checkTokenAccess("isAuthenticated()");
        security.tokenKeyAccess("isAuthenticated()");
    }


}
