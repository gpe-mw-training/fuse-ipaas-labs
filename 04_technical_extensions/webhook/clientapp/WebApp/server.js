var express = require('express'),
    app     = express(),    
    cors = require('cors');
path = require('path');
const https = require('https');
const fs = require('fs');


app.engine('html', require('ejs').renderFile);
app.use('/images',express.static(path.join(__dirname , 'images')));
app.use('/lib',express.static(path.join(__dirname, 'lib')));
app.use('/files',express.static(path.join(__dirname, 'files')));

cors({credentials: true, origin: true});
app.use(cors());


var port = process.env.PORT || process.env.OPENSHIFT_NODEJS_PORT || 8080,
    ip   = process.env.IP   || process.env.OPENSHIFT_NODEJS_IP || '0.0.0.0';


var quotesURL=process.env.QUOTES_URL;
var quotesSecret=process.env.QUOTES_SECRET;
var quotesClientId=process.env.QUOTES_CLIENTID;
var ssoURL = process.env.SSO_URL;

var quotesSpec=null;

process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";

https.get(quotesURL, function(res){
    var body = '';

    res.on('data', function(chunk){
        body += chunk;
    });

    res.on('end', function(){
    	fs.writeFile('files/IgniteQuoting.json', body, (err) => {  
    	    // throws an error, you could also catch it here
    	    if (err) throw err;

    	    // success case, the file was saved
    	    console.log('swagger doc saved!');
    	});
        quotesSpec=body;
    	console.log("GOT: " + quotesSpec);
    	startServer();
    });
}).on('error', function(e){
      console.log("Got an error: ", e);
});


function startServer(){
	var envVars = {quotesSecret: quotesSecret, quotesClientId: quotesClientId, ssoURL: ssoURL };

	app.get('/', function (req, res) {
		req.
		res.locals.envVars = envVars;
		res.render('index.html');

	});

	app.listen(port, ip);
	console.log('Server running on http://%s:%s', ip, port);

}

module.exports = app ;

