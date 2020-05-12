package config;

import common.ServiceClientInterface;
import common.ServiceMovieInterface;
import common.ServiceAcquisitionInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ui.Console;

@Configuration
public class ClientConfig {
    @Bean
    Console console(){
        return new Console();
    }

    @Bean
    RmiProxyFactoryBean serviceMovie() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(ServiceMovieInterface.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/ServiceMovieInterface");
        return rmiProxyFactoryBean;
    }
    @Bean
    RmiProxyFactoryBean serviceClient() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(ServiceClientInterface.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/ServiceClientInterface");
        return rmiProxyFactoryBean;
    }
    @Bean
    RmiProxyFactoryBean serviceRent() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(ServiceAcquisitionInterface.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/ServiceAcquisitionInterface");
        return rmiProxyFactoryBean;
    }
}
