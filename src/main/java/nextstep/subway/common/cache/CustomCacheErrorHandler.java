package nextstep.subway.common.cache;

import org.slf4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

public class CustomCacheErrorHandler implements CacheErrorHandler {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(CustomCacheErrorHandler.class);

  @Override
  public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
    log.error("Cache Get 실패 : " + cache.getName() + " : " + exception.getMessage());
  }

  @Override
  public void handleCachePutError(RuntimeException exception, Cache cache, Object key,
      Object value) {
    log.error("Cache Put 실패 :  " + cache.getName() + " : " + exception.getMessage());
  }

  @Override
  public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
    log.error("Cache Evict 실패 :  " + cache.getName() + " : " + exception.getMessage());
  }

  @Override
  public void handleCacheClearError(RuntimeException exception, Cache cache) {
    log.error("Cache Clear 실패 :  " + cache.getName() + " : " + exception.getMessage());
  }
}
