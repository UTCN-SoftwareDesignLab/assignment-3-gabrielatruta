import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allPatients() {
        return HTTP.get(BASE_URL + "/patients", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(item) {
        return HTTP.post(BASE_URL + "/patients", item, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(item) {
        return HTTP.put(BASE_URL + "/patients/" + item.id, item, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },
    delete(id) {
        return HTTP.delete(BASE_URL + "/patients/" + id, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },
};
