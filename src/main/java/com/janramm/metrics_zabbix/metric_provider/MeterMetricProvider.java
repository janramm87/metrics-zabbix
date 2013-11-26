package com.janramm.metrics_zabbix.metric_provider;

import com.codahale.metrics.Meter;
import com.janramm.metrics_zabbix.zabbix_key_enums.TimeBasedValueKeys;
import com.quigley.zabbixj.metrics.MetricsException;
import com.quigley.zabbixj.metrics.MetricsKey;
import com.quigley.zabbixj.metrics.MetricsProvider;


/**
 * Metrics Zabbix adapter for the {@link com.codahale.metrics.Meter} implementation
 * @author jan.ramm
 *
 */
public class MeterMetricProvider implements MetricsProvider {
  private final Meter meter;

  public MeterMetricProvider(Meter meter) {
    this.meter = meter;
  }

  @Override
  public Object getValue(MetricsKey mKey) throws MetricsException {
    TimeBasedValueKeys meterValueKey = TimeBasedValueKeys.valueOf(mKey.getKey());
    switch (meterValueKey) {
      case COUNT:
        return meter.getCount();
      case FIFTEEN_MINUTE_RATE:
        return meter.getFifteenMinuteRate();
      case FIVE_MINUTE_RATE:
        return meter.getFiveMinuteRate();
      case MEAN_RATE:
        return meter.getMeanRate();
      case ONE_MINUTE_RATE:
        return meter.getOneMinuteRate();
    }
    throw new MetricsException("Key " + mKey.getKey() + " not found");
  }

}
