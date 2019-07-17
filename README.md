# Instrumentation Aspects for Black-Box Drivers

This repository contains AspectJ aspects that instrument certain drivers for black-box products used in Feedzai's projects. 

The goal of this instrumentation is to extract the IP address of the black-box's node that was part of the current execution, or other metrics that might help Feedzai's monitoring endeavours.

# Building

To build simply execute:

`mvn clean install -Paspectj`

The compiled JARs in `${project.root}/${module}/target` will include the instrumented driver as a transitive dependency.

# Releasing

This project uses the [Multi-Module Maven Release Plugin](http://danielflower.github.io/multi-module-maven-release-plugin/index.html)

To deploy all changed modules run the following command:

`mvn releaser:release`

This will publish all commited changes since the previous release to the Maven repository and increment the patch version of each changed module.

It's also possible to release a single module at a time:

`mvn releaser:release -DmodulesToRelease=MyModule`


# Contributing

To add new black-boxes simply create a new module that depends on the black-box's driver. You can then include the aspect as a Java file in this module.



