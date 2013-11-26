package com.janramm.metrics_zabbix.metric_provider;
import com.codahale.metrics.Snapshot;
import com.codahale.metrics.Timer;
import com.janramm.metrics_zabbix.zabbix_key_enums.SnapshotValueKeys;
import com.janramm.metrics_zabbix.zabbix_key_enums.TimeBasedValueKeys;
import com.quigley.zabbixj.metrics.MetricsException;
import com.quigley.zabbixj.metrics.MetricsKey;
import com.quigley.zabbixj.metrics.MetricsProvider;


public class TimerMetricProvider implements MetricsProvider {
  private final Timer timer;



  public TimerMetricProvider(Timer timer) {
    this.timer = timer;
  }



  @Override
  public Object getValue(MetricsKey mKey) throws MetricsException {
    try {
      TimeBasedValueKeys meterValueKey = TimeBasedValueKeys.valueOf(mKey.getKey());
      switch (meterValueKey) {
        case COUNT:
          return timer.getCount();
        case FIFTEEN_MINUTE_RATE:
          return timer.getFifteenMinuteRate();
        case FIVE_MINUTE_RATE:
          return timer.getFiveMinuteRate();
        case MEAN_RATE:
          return timer.getMeanRate();
        case ONE_MINUTE_RATE:
          return timer.getOneMinuteRate();
      }
    } catch (IllegalArgumentException iae) {
      //ignore exception because it can be of the second type
    }
    SnapshotValueKeys histogramValueKey = SnapshotValueKeys.valueOf(mKey.getKey());
    Snapshot snapshot = timer.getSnapshot();
    switch (histogramValueKey) {
      case MEAN:
        return snapshot.getMean();
      case MAX:
        return snapshot.getMax();
      case MIN:
        return snapshot.getMin();
      case MEDIAN:
        return snapshot.getMedian();
      case P75TH:
        return snapshot.get75thPercentile();
      case P95TH:
        return snapshot.get95thPercentile();
      case P98TH:
        return snapshot.get98thPercentile();
      case P99TH:
        return snapshot.get99thPercentile();
      case P999TH:
        return snapshot.get999thPercentile();
      case STDDEV:
        return snapshot.getStdDev();
      case COUNT:
        return timer.getCount();
    }
    throw new MetricsException("Key " + mKey.getKey() + " not found");

  }
}
