#!/bin/bash

java $SBT_OPTS -Dfile.encoding=UTF-8 -Xss4M -Xmx1024M -XX:MaxPermSize=256M -XX:NewSize=128M -XX:NewRatio=3 -jar `dirname $0`/sbt-launch-0.7.3.jar @sbt.boot.properties "$@"
