package com.janramm.metrics_zabbix;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricRegistryListener;
import com.codahale.metrics.Timer;
import com.janramm.metrics_zabbix.metric_provider.CounterMetricProvider;
import com.janramm.metrics_zabbix.metric_provider.GaugeMetricProvider;
import com.janramm.metrics_zabbix.metric_provider.HistogramMetricProvider;
import com.janramm.metrics_zabbix.metric_provider.MeterMetricProvider;
import com.janramm.metrics_zabbix.metric_provider.TimerMetricProvider;
import com.quigley.zabbixj.agent.ZabbixAgent;

/**
 * This class is a MetricRegistryListener and hooks up the Zabbix agent with the Metrics-Measures.
 * When a Metric is added the Metric gets registered to the Zabbix agent.
 * @author jan.ramm
 *
 */
public class ZabbixMetricsAgent implements MetricRegistryListener {
  private final ZabbixAgent agent;
  private final MetricRegistry metrics;


  public ZabbixMetricsAgent(MetricRegistry metrics, ZabbixAgent agent) {
    this.metrics = metrics;
    this.agent = agent;
  }

  /**
   * Register this agent as metrics listener in order to get all added metrics.
   * This method should be called, before any metric is added otherwise the metrics won't get reported to zabbix
   */
  public void start() {
    this.metrics.addListener(this);
  }


  /**
   * Removes this agent as metrics listener
   */
  public void stop() {
    this.metrics.removeListener(this);
  }


  @Override
  public void onGaugeAdded(final String name, final Gauge<?> gauge) {
    this.agent.addProvider(name, new GaugeMetricProvider(gauge));
  }



  @Override
  public void onCounterAdded(final String name, final Counter counter) {
    this.agent.addProvider(name, new CounterMetricProvider(counter));
  }



  @Override
  public void onHistogramAdded(final String name, final Histogram histogram) {
    this.agent.addProvider(name, new HistogramMetricProvider(histogram));

  }

  @Override
  public void onMeterAdded(final String name, final Meter meter) {
    this.agent.addProvider(name, new MeterMetricProvider(meter));

  }


  @Override
  public void onTimerAdded(final String name, final Timer timer) {
    this.agent.addProvider(name, new TimerMetricProvider(timer));

  }


  @Override
  public void onCounterRemoved(String name) {
    //do nothing because zabbixj does not support to remove a metric
  }

  @Override
  public void onHistogramRemoved(String name) {
    //do nothing because zabbixj does not support to remove a metric

  }

  @Override
  public void onMeterRemoved(String name) {
    //do nothing because zabbixj does not support to remove a metric
  }


  @Override
  public void onGaugeRemoved(final String name) {
    //do nothing because zabbixj does not support to remove a metric 
  }


  @Override
  public void onTimerRemoved(String name) {
    //do nothing because zabbixj does not support to remove a metric
  }

}
