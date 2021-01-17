package nextstep.subway.common.config;

import nextstep.subway.common.cache.CustomCacheErrorHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "ehcache")
public class EhCacheConfig extends CachingConfigurerSupport implements CachingConfigurer {

  @Bean
  public CacheManager cacheManager() {
    EhCacheCacheManager cacheManager = new EhCacheCacheManager();
    cacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
    return cacheManager;
  }

  @Bean
  public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
    EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
    factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
    return factory;
  }


  @Override
  public CacheErrorHandler errorHandler() {
    return new CustomCacheErrorHandler();
  }
}
