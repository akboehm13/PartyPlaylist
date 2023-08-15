import axios from "axios";

export default {
    list() {
        return axios.get('/songs');
    },
    get(id) {
        return axios.get(`/songs/${id}`);
    },
    add(song) {
        return axios.post('/songs', song);
    },
    update(id, song) {
        return axios.put(`/songs/${id}`, song);
    },
    delete(id) {
        return axios.delete(`/songs/${id}`);
    }
}