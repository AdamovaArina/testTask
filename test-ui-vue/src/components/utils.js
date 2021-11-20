export default {
    sendAjaxRequest(url, httpMethod, data, successCallback)
    {
        var request = new XMLHttpRequest();
        request.open(httpMethod, url);
        request.setRequestHeader('Content-Type', 'application/json');
        request.addEventListener('readystatechange', () => {
            if (request.readyState === 4 && request.status === 200) {
                if (JSON.parse(request.responseText).status === true){
                    successCallback(JSON.parse(request.responseText));
                }
            }
        });
        if (data != null) {
            request.send(data);
        }
        else {
            request.send();
        }
    }
}
