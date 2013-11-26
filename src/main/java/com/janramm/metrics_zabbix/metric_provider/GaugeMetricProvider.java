package com.janramm.metrics_zabbix.metric_provider;
import com.codahale.metrics.Gauge;
import com.quigley.zabbixj.metrics.MetricsException;
import com.quigley.zabbixj.metrics.MetricsKey;
import com.quigley.zabbixj.metrics.MetricsProvider;


public class GaugeMetricProvider implements MetricsProvider {
  private final Gauge<?> gauge;


  public GaugeMetricProvider(Gauge<?> gauge) {
    this.gauge = gauge;
  }


  @Override
  public Object getValue(MetricsKey mKey) throws MetricsException {
    return gauge.getValue();
  }
}
