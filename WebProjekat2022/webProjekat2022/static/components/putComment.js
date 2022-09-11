Vue.component("putComment",{
    data : function(){
        return{
            comment : {id : null,facilityID : null,customerID : null,commentText : null,stars : null,available : null,
                isDeleted : null}
        }
    },
    template:
    `
    <div>
    <p>Ostavi komentar</p>
    <table>
        <tr><td>Komentar:</td><td><textarea style="height : 5cm" id="commentText" v-model="comment.commentText"></textarea></td></tr>
        <tr><td>Zvezde:</td><td><input type="number" min="1" max="10" id="stars" v-model="comment.stars"></td></tr>
        <tr><td><button v-on:click = "addComment"  style="padding: 7px 20px;
            background-color: aqua;">Dodaj komentar</button></td></tr>
    </table>
    </div>
    `,
    mounted(){

    },
    methods : {
        addComment : function(event){
            axios.post('rest/customerHomePage/putComment/add',this.comment).then(response => (router.push('/customerHomePage/')))
        }
    }
})