var noop = function(){};
var UserAgent = {
    setUserAgent: function (text, success, fail) {
    	text = text||""; // Empty is the same as issuing reset.
        cordova.exec(success||noop, fail||noop, "UserAgent", "setUserAgent", [text]);
    },
    getUserAgent: function (success, fail) {
        if (success) {
           cordova.exec(success, fail||noop, "UserAgent", "getUserAgent", []);
         } else {
           return false;
        }
    },
    setOrigin: function (text, success, fail) {
    	text = text||""; // Empty is the same as issuing reset.
        cordova.exec(success||noop, fail||noop, "UserAgent", "setOrigin", [text]);
    },
    getOrigin: function (success, fail) {
        if (success) {
           cordova.exec(success, fail||noop, "UserAgent", "getOrigin", []);
         } else {
           return false;
        }
    },
    reset: function (success, fail) {
        cordova.exec(success||noop, fail||noop, "UserAgent", "reset", []);
    }
};
module.exports = UserAgent;
