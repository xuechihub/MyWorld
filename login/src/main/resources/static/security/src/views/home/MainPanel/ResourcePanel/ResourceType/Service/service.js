import { service } from '../../../../../../assets/js/utils.js';
import _ from 'lodash';

export const getUserList = (tableHeader) => {
    let columns = '';
    _.forEach(tableHeader, (head, index) => {
        if (index === 0) {
            columns = head.id;
        } else {
            columns += ',' + head.id;
        }
    });
    let params = {
        'columns': columns,
        'filter': 'baseorgId=4081f9f64936db60014936ed28060008',
        'pageIndex': 1,
        'pageSize': 20
    };
    return service(`/rest/iscUser/?params=${encodeURIComponent(JSON.stringify(params))}`)
        .then((response) => {
            return response.resultValue;
        })
        .catch((error) => {
            return [];
        });
};

// module.exports = {
//     getUserList
// };
