import axios from "axios";

export default {
    list() {
        return axios.get('/events');
    },
    get(id) {
        return axios.get(`/events/${id}`);
    },
    add(event) {
        return axios.post('/events', event);
    },
    update(id, event) {
        return axios.put(`/songs/${id}`, event);
    },
    delete(id) {
        return axios.delete(`/events/${id}`);
    }
}