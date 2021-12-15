import axios from "axios";

export default {
    compile(execution) {
        return axios.post('/compiler', execution);
    }
}