package com.janramm.metrics_zabbix.zabbix_key_enums;

/**
 * This are the keys as they are expected to be specified in zabbix, for {@link com.codahale.metrics.Histogram} or {@link com.codahale.metrics.Timer}
 * @author jan.ramm
 *
 */
public enum SnapshotValueKeys {
  MAX,
  MIN,
  MEDIAN,
  MEAN,
  STDDEV,
  P75TH,
  P95TH,
  P98TH,
  P99TH,
  P999TH,
  COUNT

}
