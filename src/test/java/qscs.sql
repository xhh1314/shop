Truncate Table Temp_JQ_MainCode
Insert Into Temp_JQ_MainCode(Code) Values('60019915') 

Select TempKM.Code KMCode ,'' HBCode,
       Sum(DF) 
 from   


(select A.code,km.name,Case When km.balanorient = 0 Then 1 Else -1 End as YEOrient 
	from 
	bd_accasoa soa,bd_account km,bd_accchart chart,org_accountingbook zb, Temp_JQ_MainCode A 
	where soa.pk_account = km.pk_account 
	  and chart.pk_accchart = soa.pk_accchart 
	  and chart.originalchart = zb.pk_curraccchart 
	  and km.code = A.code 
 	  and zb.code= 'A10502-0001' ) TempKM,
 (select km.code kmcode,
        
        zb.code bookcode,
        ye.year ztyear,
        '00' period,
        hb.code hbcode,
        ye.localdebitamount jf,
        ye.localcreditamount df,
        ye.debitamount wjf,
        ye.creditamount wdf,
        ye.debitquantity sjf,
        ye.creditquantity sdf 
 from gl_balance ye inner join org_accountingbook zb on ye.pk_accountingbook=zb.pk_accountingbook    
                       inner join bd_currtype HB on  ye.Pk_Currtype=HB.Pk_Currtype 
                       inner join bd_accasoa soa on soa.pk_accasoa = ye.pk_accasoa 
                       inner join bd_account km on km.pk_account = soa.pk_account 
 where ye.year = '2017' and ye.period = '00' 
 union all 
 select km.code kmcode,
        
        zb.code bookcode,
        pz.year ztyear,
        pz.period period,
        hb.code hbcode,       
        pzx.localdebitamount jf,
        pzx.localcreditamount df,
        pzx.debitamount wjf,
        pzx.creditamount wdf,
        pzx.debitquantity sjf,
        pzx.creditquantity sdf      
    from 
           gl_detail pzx  inner join gl_voucher pz  on  pz.pk_voucher=pzx.pk_voucher and pz.pk_accountingbook=pzx.pk_accountingbook    
                        inner join org_accountingbook zb on pz.pk_accountingbook=zb.pk_accountingbook
                        inner join bd_currtype hb on  pzx.pk_currtype=hb.pk_currtype 
                        inner join bd_accasoa soa on  soa.pk_accasoa=pzx.pk_accasoa
                        inner join bd_account km  on  soa.pk_account=km.pk_account
 where pz.discardflag='N' And pz.dr=0 And pz.voucherkind<>255 And pz.errmessage ='~' 
	and pz.year ='2017' and pz.period > '00'	)master
 where Master.ZTYear = '2017'  And Master.Period>'00' And Master.Period<='05' 
       And Master.BookCode = 'A10502-0001' 
       And Master.KMCode Between TempKM.Code And TempKM.Code||'z' 
        
        
       Group by TempKM.Code ,TempKM.YEOrient
