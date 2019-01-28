var exec = require('cordova/exec');

exports.click = function (clickUrl, success, error) {
    exec(success, error, 'adMonitor', 'click', [clickUrl]);
};

exports.expose = function (exposeUrl, success, error) {
    exec(success, error, 'adMonitor', 'expose', [exposeUrl]);
};
