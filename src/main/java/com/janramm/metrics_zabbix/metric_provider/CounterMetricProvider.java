package com.janramm.metrics_zabbix.metric_provider;
import com.codahale.metrics.Counter;
import com.quigley.zabbixj.metrics.MetricsException;
import com.quigley.zabbixj.metrics.MetricsKey;
import com.quigley.zabbixj.metrics.MetricsProvider;


public class CounterMetricProvider implements MetricsProvider {

  private final Counter counter;

  public CounterMetricProvider(Counter counter) {
    this.counter = counter;
  }



  @Override
  public Object getValue(MetricsKey mKey) throws MetricsException {
    return counter.getCount();
  }
}
