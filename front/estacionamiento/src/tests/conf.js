exports.config = {
    framework: 'jasmine',
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['registro.js'],
    capabilities: {
        browserName: 'chrome'
    }
}