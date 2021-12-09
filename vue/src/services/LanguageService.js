import axios from 'axios';

export default {
    getAllLanguages() {
        return axios.get('/languages');
    },
    addLanguage(language) {
        return axios.post('/languages', language);
    },
    updateLanguage(language) {
        return axios.put('/languages', language);
    }
}