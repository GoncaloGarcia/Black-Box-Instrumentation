#!/usr/bin/env bash

if [ "$TRAVIS_PULL_REQUEST" = "false" ] && echo "$TRAVIS_COMMIT_MESSAGE" | grep -q "[RELEASE]"; then
    echo "TRAVIS TAG: $TRAVIS_TAG"
    mvn --settings .m2/settings.xml releaser:release
    exit $?
fi