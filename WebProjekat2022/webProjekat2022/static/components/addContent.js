Vue.component("addContent",{
    data : function(){
        return{
            contentToSend : {nameID : null,facilityName : null,name : null,type : null,coach : null,
                logo : null,description : null,duration : null,startTime : null,endTime : null}
        }
    },
    template:
    `
    <div>
    <table>
        <tr><td>Content name:</td>
        <td><input type="text" id="name" v-model="contentToSend.name"/></td></tr>
        <tr><td>Content type:</td>
        <td>
        <select type="text" value="" id="type" v-model="contentToSend.type">
            <option>GROUP_TRAINING</option>
            <option>PERSONAL_TRAINING</option>
            <option>SAUNA</option>
        </select>
        </td></tr>
        <tr><td>Logo:</td>
        <td><input type="text" id="logo" v-model="contentToSend.logo"/></td></tr>
        <tr><td>Description:</td>
        <td><input type="text" id="description" v-model="contentToSend.description"/></td></tr>
        <tr><td>Datum:</td>
        <td><input type="date" id="duration" v-model="contentToSend.duration"/></td></tr>
        <tr><td>Pocetak</td>
        <td><input type="time" id="startTime" v-model="contentToSend.startTime"/></td></tr>
        <tr><td>Kraj</td>
        <td><input type="time" id="endTime" v-model="contentToSend.endTime"/></td></tr>
        <tr><td><button v-on:click = "createContent"  style="padding: 7px 20px;
        background-color: aqua;">Dodaj</button></td></tr>
    </table>
    </div>
    `,
    mounted(){

    },
    methods : {
        createContent : function(event){
            axios.post('rest/managerHomePage/addContent/',this.contentToSend).then(response => (alert("Content added")))
            .catch(error => (alert("Content not added")));
            event.preventDefault();
            router.push('/managerHomePage/')
        }
    }
})