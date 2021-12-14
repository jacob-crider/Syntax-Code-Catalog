import axios from 'axios';

export default {
    getAllExamples() {
        return axios.get('/examples');
    },
    addExample(example) {
        return axios.post('/examples', example);
    },
    updateExample(example) {
        return axios.put('/examples', example);
    },
    addComment(comment) {
        return axios.post('/addComment', comment);
    }
}