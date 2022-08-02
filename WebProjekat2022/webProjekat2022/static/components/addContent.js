Vue.component("addContent",{
    data : function(){
        return{
            contentToSend : {name:null,facilityType:null,contentType:null,status:null,logo:null,
                latitude:null,longitude:null,street:null,number:null,city:null,country:null,
                workingHours:null,rating:null,manager:null}
        }
    },
    template:
    `
    <div>
        <tr><td></td></tr>
        <select type="text" value="" id="type" v-model="contentToSend.contentType">
            <option>GROUP_TRAINING</option>
            <option>PERSONAL_TRAINING</option>
            <option>SAUNA</option> 
        </select>
        </b>
        <button v-on:click = "createContent"  style="padding: 7px 20px;
                background-color: aqua;">Dodaj</button>
    </div>
    `,
    mounted(){

    },
    methods : {
        createContent : function(event){
            axios.post('rest/managerHomePage/addContent/',this.contentToSend).then(response => (alert("Content added")));
            event.preventDefault();
            router.push('/managerHomePage/')
        }
    }
})