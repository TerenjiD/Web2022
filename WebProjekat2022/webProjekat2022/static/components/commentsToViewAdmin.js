Vue.component("commentsToViewAdmin",{
    data : function(){
        return{
            comments : null
        }
    },
    template:
    `
    <div>
    <table v-for="(p,index) in comments">
        <tr><td>Objekat:</td><td>{{p.facilityID}}</td></tr>
        <tr><td>Korisnik:</td><td>{{p.customerID}}</td></tr>
        <tr><td>Komentar:</td><td>{{p.commentText}}</td></tr>
        <tr><td>Zvezde:</td><td>{{p.stars}}</td></tr>
        <tr><td>Odobren:</td><td v-if="p.available === 0">Nije</td>
        <td v-else>Jeste</td></tr>
    </table>
    <td><button v-on:click="goBack" style="padding: 7px 20px;
        background-color: aqua;">Vrati se</button></td></tr>
    </div>
    `,
    mounted(){
        axios.get('rest/adminHomePage/viewCommentsForAdmin').then(response => (this.comments = response.data))
    },
    methods : {
        goBack : function(){
            router.push('rest/adminHomePage')
        }
    }
})