SLA Alert Mail thourgh SendGrid Service

## How to set up
1. add this clases into your class path as Kylo pick up Plugins for SLA through Reflection
2. create sla.sendgrid.email.properties and make sure it can be loaded from Kylo through classpath
  ** resources/conf has an example config file. You create it.


## Notice
1. I created this plugin for fun. So, I am not implementing some configuration validation logic. So, be careful. 

I tested this module with Kylo 0.8.3.2-SNAPSHOT
