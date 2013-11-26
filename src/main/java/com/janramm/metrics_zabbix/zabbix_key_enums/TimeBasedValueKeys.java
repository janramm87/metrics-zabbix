package com.janramm.metrics_zabbix.zabbix_key_enums;

/**
 * This are the keys as they are expected to be specified in zabbix, for {@link com.codahale.metrics.Meter} or {@link com.codahale.metrics.Timer}
 * @author jan.ramm
 *
 */
public enum TimeBasedValueKeys {
  MEAN_RATE,
  ONE_MINUTE_RATE,
  FIVE_MINUTE_RATE,
  FIFTEEN_MINUTE_RATE,
  COUNT
}
