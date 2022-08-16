const TerserPlugin = require('terser-webpack-plugin');

module.exports = {
    mode: "production",
    entry: ['./keccak256.js'],
    target: 'node',
    optimization: {
        minimizer: [new TerserPlugin({ terserOptions: { mangle: false } })]
    },
    output: {
        path: `${process.cwd()}/bin`,
        filename: 'keccak256.js',
        libraryTarget: 'umd'
    }
};
