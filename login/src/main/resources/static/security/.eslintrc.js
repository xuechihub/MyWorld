module.exports = {
    root: true,
    env: {
        node: true
    },
    'extends': [
        'plugin:vue/essential',
        '@vue/standard'
    ],
    rules: {
        'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
        'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
        indent: ['error', 4],
        'semi': ['error', 'always'],
        'vue/script-indent': ['error', 4, {
            'baseIndent': 1,
            'switchCase': 0,
            'ignores': []
        }]
    },
    overrides: [
        {
            'files': ['*.vue'],
            'rules': {
                'indent': 'off'
            }
        }
    ],
    parserOptions: {
        parser: 'babel-eslint'
    }
};
