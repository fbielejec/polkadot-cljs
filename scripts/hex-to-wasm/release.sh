#!/bin/bash

set -e

yarn deps
yarn shadow-cljs release hex-to-wasm
yarn webpack --config webpack.config.js
mv ./bin/hex-to-wasm.js hex-to-wasm.js

echo "Done"
exit $?
