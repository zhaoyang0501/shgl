jQuery.adminCar = {
		carDataTable:null,
		toSave:false,
		initSearchDataTable : function() {
			if (this.carDataTable == null) {
				this.carDataTable = $('#dt_table_view').dataTable({
					"sDom" : "<'row-fluid'<'span6'l>r>t<'row-fluid'<'span6'i><'span6'p>>",
					"sPaginationType" : "bootstrap",
					"oLanguage" : {
						"sLengthMenu" : "每页显示 _MENU_ 条记录",
						"sZeroRecords" : "抱歉， 暂时没有记录",
						"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
						"sSearch" : "",
						"sInfoEmpty" : "没有数据",
						"sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
						"oPaginate" : {
							"sFirst" : "首页",
							"sPrevious" : "前一页",
							"sNext" : "后一页",
							"sLast" : "尾页"
						}
					},
					"bAutoWidth" : false,
					"iDisplayLength" : 10,
					"aLengthMenu" : [ 5, 10, 25, 50],
					"bServerSide" : true,
					"sServerMethod" : "POST",
					"bProcessing" : true,
					"bSort" : false,
					"sAjaxSource" : $.ace.getContextPath() + "/admin/car/list",
					"fnDrawCallback" : function(oSettings) {
						$('[rel="popover"],[data-rel="popover"]').popover();
					},
					"fnServerData" : function(sSource, aoData, fnCallback) {
						var name = $("#_name").val();
						if (!!name) {
							aoData.push({
								"name" : "name",
								"value" : name
							});
						}
						$.ajax({
							"dataType" : 'json',
							"type" : "POST",
							"url" : sSource,
							"data" : aoData,
							"success" : function(data){
								fnCallback(data.resultMap);
							}
						});
					},
					"aoColumns" : [{
						"mDataProp" : "id"
					},{
						"mDataProp" : "category.name"
					}, {
						"mDataProp" : "engineNo"
					}, {
						"mDataProp" : "owner"
					}, {
						"mDataProp" : "trademark"
					}, {
						"mDataProp" : "color"
					}, {
						"mDataProp" : "gearbox"
					}, {
						"mDataProp" : ""
					}],
					"aoColumnDefs" : [
						{
							'aTargets' : [7],
							'fnRender' : function(oObj, sVal) {
								return "<button class=\"btn2 btn-info\" onclick=\"$.adminCar.showEdit('"+oObj.aData.id+"')\"><i class=\"icon-pencil\"></i>修改</button>"+
								 "  <button class=\"btn2 btn-info\" onclick=\"$.adminCar.deleteCar('"+oObj.aData.id+"')\"><i class=\"icon-trash\"></i> 删除</button>";
							}
						},
					 {
						'aTargets' : [ '_all' ],
						'bSortable' : false,
						'sClass' : 'center'
					}]

				});
			} else {
				var oSettings = this.carDataTable.fnSettings();
				oSettings._iDisplayStart = 0;
				this.carDataTable.fnDraw(oSettings);
			}

		},
		deleteCar :function(id){
			bootbox.confirm( "是否确认删除？", function (result) {
	            if(result){
	            	$.ajax({
	        			type : "post",
	        			url : $.ace.getContextPath() + "/admin/car/delete",
	        			dataType : "json",
	        			data:"id="+id,
	        			success : function(json) {
	        				if(json.resultMap.state=='success'){
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	        					$.adminCar.initSearchDataTable();
	        				}else{
	        					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	        				}
	        			}
	        		});
	            }
	        });
		},
		showaddModal: function(id){
			$.adminCar.toSave=true;
			$("#user_modal_header_label").text("新增分类");
			$("#_modal").modal('show');
			$("#id").attr("readonly",false);
		},
		save : function (){
			if($.adminCar.toSave){
				$.ajax({
	    			type : "post",
	    			url : $.ace.getContextPath() + "/admin/car/save",
	    			data:{
	    				"car.id":$("#id").val(),
	    				"car.category.id":$("#categoryid").val(),
	    				"car.engineNo":$("#engineNo").val(),
	    				"car.owner":$("#owner").val(),
	    				"car.trademark":$("#trademark").val(),
	    				"car.color":$("#color").val(),
	    				"car.gearbox":$("#gearbox").val()
	    			},
	    			dataType : "json",
	    			success : function(json) {
	    				if(json.resultMap.state=='success'){
	    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	    					$.adminCar.initSearchDataTable();
	    					$("#_modal").modal('hide');
	    				}else{
	    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	    				}
	    			}
	    		});
			}else{
				$.ajax({
	    			type : "post",
	    			url : $.ace.getContextPath() + "/admin/car/update",
	    			data:{
	    				"car.id":$("#id").val(),
	    				"car.category.id":$("#categoryid").val(),
	    				"car.engineNo":$("#engineNo").val(),
	    				"car.owner":$("#owner").val(),
	    				"car.trademark":$("#trademark").val(),
	    				"car.color":$("#color").val(),
	    				"car.gearbox":$("#gearbox").val()
	    			},
	    			dataType : "json",
	    			success : function(json) {
	    				if(json.resultMap.state=='success'){
	    					$("#user_edit_modal").modal('hide');
	    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"success","timeout":"2000"});
	    					$.adminCar.initSearchDataTable();
	    					$("#_modal").modal('hide');
	    				}else{
	    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
	    				}
	    			}
	    		});
			}
		},
		showEdit: function (id){
			$("#id").val(id);
			$.adminCar.toSave=false;
			$.ajax({
    			type : "post",
    			url : $.ace.getContextPath() + "/admin/car/get",
    			dataType : "json",
    			data:"id="+id,
    			success : function(json) {
    				if(json.resultMap.state=='success'){
    					$("#user_modal_header_label").text("修改分类");
    					$("#id").attr("readonly","readonly");
    					$("#_modal").modal('show');
    					$("#categoryid").val(json.resultMap.object.categoryid);
    					$("#engineNo").val(json.resultMap.object.engineNo);
    					$("#owner").val(json.resultMap.object.owner);
    					$("#trademark").val(json.resultMap.object.trademark);
    					$("#color").val(json.resultMap.object.color);
    					$("#gearbox").val(json.resultMap.object.gearbox);
    					
    				}else{
    					noty({"text":""+ json.resultMap.msg +"","layout":"top","type":"warning"});
    				}
    			}
    		});
		}
};