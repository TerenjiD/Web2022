Vue.component("changeContent",{
    data : function(){
        return{
            loadContent : []
        }
    },
    template:
    `
    <div>
    <table>
        <tr><td>Content name:</td>
        <td><input type="text" id="name" v-model="loadContent.name"/></td></tr>
        <tr><td>Content type:</td>
        <td>
        <select type="text" value="" id="type" v-model="loadContent.type">
            <option>GROUP_TRAINING</option>
            <option>PERSONAL_TRAINING</option>
            <option>SAUNA</option> 
        </select>
        </td></tr>
        <tr><td>Logo:</td>
        <td><input type="text" id="logo" v-model="loadContent.logo"/></td></tr>
        <tr><td>Description:</td>
        <td><input type="text" id="description" v-model="loadContent.description"/></td></tr>
        <tr><td>Duration:</td>
        <td><input type="text" id="duration" v-model="loadContent.duration"/></td></tr>
        <tr><td><button v-on:click = "changeContent"  style="padding: 7px 20px;
        background-color: aqua;">Izmeni</button></td>
        <td><button v-on:click = "backToHome"  style="padding: 7px 20px;
        background-color: aqua;">Vrati se</button></td>
        </tr>
    </table>
    </div>
    `
    ,mounted(){
        axios.get('rest/managerHomePage/setContent/').then(response => (this.loadContent=response.data))
    },methods : { 
        changeContent : function(event){
            axios.post('rest/managerHomePage/changeContent/',this.loadContent)
            router.push('/managerHomePage/')
        },
        backToHome : function(){
            router.push('/managerHomePage/')
        }
    }
})