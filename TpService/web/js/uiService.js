class UIService {
    getFetch(url){
        return fetch(url)
        .then(response => response.json())
        .catch(function (err) {
            op.saveErrorsList(err);
            msg.danger()
        })
    }
    postFetch(urls,info){
        return fetch(urls, {
            method: 'POST',
            body: info
        })
        .then(response => response.json()) 
        .then(data => {
            if (data){return data;}
        })
        .catch(function (err) {
            op.saveErrorsList(err);
            msg.danger()
        })
    }
}
const ser = new UIService()