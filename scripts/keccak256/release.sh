#!/bin/bash

set -e

yarn deps
yarn shadow-cljs release keccak256
yarn webpack --config webpack.config.js
mv ./bin/keccak256.js keccak256.js

echo "Done"
exit $?
