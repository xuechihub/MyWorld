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

// module.exports = {
//     service: service
// };
