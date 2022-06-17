const TerserPlugin = require('terser-webpack-plugin');

module.exports = {
    mode: "production",
    entry: ['./hex-to-wasm.js'],
    target: 'node',
    optimization: {
        minimizer: [new TerserPlugin({ terserOptions: { mangle: false } })]
    },
    output: {
        path: `${process.cwd()}/bin`,
        filename: 'hex-to-wasm.js',
        libraryTarget: 'umd'
    }
};
