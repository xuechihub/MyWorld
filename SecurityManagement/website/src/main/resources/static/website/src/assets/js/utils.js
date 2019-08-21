import fetch from 'isomorphic-fetch';

export const service = (url, options) => {
    return fetch(`${process.env.BASE_URL}${url}`, {
        method: 'get',
        headers: {
            'Content-Type': 'application/json'
        },
        ...options
    }).catch((error) => {
        Promise.reject(error);
    }).then((response) => {
        return response.json();
    });
};

export const parseJson2Url = (data) => {
    var _result = [];
    for (var key in data) {
        var value = data[key];
        if (typeof (value) !== 'string') {
            _result.push(key + '=' + encodeURIComponent(JSON.stringify(value)));
        } else {
            _result.push(key + '=' + value);
        }
    }

    return _result.join('&');
};

// module.exports = {
//     service: service
// };
