# Instrumentation Aspects for Black-Box Drivers

This repository contains AspectJ aspects that instrument certain drivers for black-box products used in Feedzai's projects. 

The goal of this instrumentation is to extract the IP address of the black-box's node that was part of the current execution, or other metrics that might help Feedzai's monitoring endeavours.

# Building

To build simply execute:

`mvn clean install`

The compiled JARs in `${project.root}/${module}/target` will include the instrumented driver as a transitive dependency.

# Releasing

To perform a release simply make a commit containing the keyword [RELEASE] in the description.

# Contributing

To add new black-boxes simply create a new module that depends on the black-box's driver. You can then include the aspect as a Java file in this module.



