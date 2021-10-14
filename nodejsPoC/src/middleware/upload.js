const util = require("util");
const mongoose = require("mongoose");
const multer = require("multer");
const connection = mongoose.connect('mongodb://localhost:27017/poc_files_db');
const {GridFsStorage} = require("multer-gridfs-storage");

var storage = new GridFsStorage({
    db: connection,
  options: { useNewUrlParser: true, useUnifiedTopology: true },
  file: (req, file) => {
    const match = ["image/png", "image/jpeg"];

    if (match.indexOf(file.mimetype) === -1) {
      const filename = `${Date.now()}-poc-${file.originalname}`;
      return filename;
    }

    return {
      bucketName: "photos",
      filename: `${Date.now()}-poc-${file.originalname}`
    };
  }
});

var uploadFile = multer({ storage: storage }).single("file");
var uploadFilesMiddleware = util.promisify(uploadFile);
module.exports = uploadFilesMiddleware;