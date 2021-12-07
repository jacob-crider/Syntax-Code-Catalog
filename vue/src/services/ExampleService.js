import axios from 'axios';

export default {
    getAllExamples() {
        return axios.get('/examples');
    },
    addExample(example) {
        return axios.post('/examples', example);
    }
}