/* eslint no-use-before-define: 0 */ 
<template>
  <div>
    비디오 업로드
    <input
      id="file-selector"
      ref="file"
      type="file"
      @change="handleFileUpload()"
    />
    <v-btn @click="upload()">업로드</v-btn>
    <v-btn @click="get_file()">가져오기</v-btn>
  </div>
</template>
<script>
import AWS from "aws-sdk";
export default {
  data() {
    return {
      file: null,
      albumBucketName: "ansana-storage",
      bucketRegion: "ap-northeast-2",
      IdentityPoolId: "ap-northeast-2:fd7b179b-681d-4eb2-96ab-ace012965877"
    };
  },
  methods: {
    handleFileUpload() {
      this.file = this.$refs.file.files[0];
      console.log(this.file);
    },
    get_file() {
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId
        })
      });
      var s3 = new AWS.S3({
        apiVersion: "2006-03-01",
        params: {
          Bucket: this.albumBucketName
        }
      });
      s3.listObjects(
        {
          Delimiter: "/"
        },
        (err, data) => { // eslint-disable-line no-unused-vars
          if (err) {
            return alert("못 가져옴" + err.message);
          }
        }
      );
    },
    upload() {
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId
        })
      });
      var s3 = new AWS.S3({
        apiVersion: "2006-03-01",
        params: {
          Bucket: this.albumBucketName
        }
      });
      let photoKey = this.file.name;
      s3.upload(
        {
          Key: photoKey,
          Body: this.file,
          ACL: "public-read"
        },
        (err, data) => {
          if (err) {
            console.log(err);
            return alert(
              "There was an error uploading your photo: " + err.message
            );
          }
          alert("Successfully uploaded photo");
          //viewAlbum(albumName);
          console.log(data);
        }
      );
    }
  }
};
</script>