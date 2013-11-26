# metrics-zabbix #


This project is a small addon for metrics in order to report measures to Zabbix.

## Import with Maven: ##
 
    ...
    <dependency>
      <groupId>com.janramm</groupId>
      <artifactId>metrics-zabbix</artifactId>
      <version>0.0.1-SNAPSHOT</version>
	</dependency>
    ...


## Example: ##

Our source contains a counter called test and our host (zabbix name) is macbook-jramm

In order to monitor that counter we have to integrate some source code and have to configure zabbix.

### Source code: ###

    ZabbixAgent agent = new ZabbixAgent();
    agent.setEnableActive(true);
    agent.setEnablePassive(false);
    agent.setHostName("macbook-jramm");
    agent.setServerAddress(InetAddress.getByName("zabbix.janramm.com"));
    agent.setServerPort(10051);
    final MetricRegistry metrics = new MetricRegistry();

    ZabbixMetricsAgent zabbixMetricsAgent = new ZabbixMetricsAgent(metrics, agent);
    zabbixMetricsAgent.start();

    Counter counter = metrics.counter("test");
    agent.start();

    counter.inc();

    Thread.sleep(1000);
    counter.inc();

    Thread.sleep(35000);

    zabbixMetricsAgent.stop();
    agent.stop();
    
### Zabbix configuration: ###
* create a host called macbook-jramm
* create an item with the following configuration:
  * test.test (metricname.metric-key)
  * Zabbix agent (active)
    
#### MetricKeys ####
The supported metric-keys depends on the metric type

##### Counter #####
any metric-key specified will return the current count

##### Gauge #####
any metric-key specified will return the current count

##### Histogram #####
supported metric-keys:

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

##### Meter #####
supported metric-keys:

    MEAN_RATE,
    ONE_MINUTE_RATE,
    FIVE_MINUTE_RATE,
    FIFTEEN_MINUTE_RATE,
    COUNT
    
##### Timer #####
supported metric-keys:
  
    MEAN_RATE,
    ONE_MINUTE_RATE,
    FIVE_MINUTE_RATE,
    FIFTEEN_MINUTE_RATE,
    COUNT
    MAX,
    MIN,
    MEDIAN,
    MEAN,
    STDDEV,
    P75TH,
    P95TH,
    P98TH,
    P99TH,
    P999TH