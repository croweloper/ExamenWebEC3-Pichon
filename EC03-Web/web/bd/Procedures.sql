USE clinica;

drop procedure if exists sp_ObtenerEspecialidades;
delimiter &&
create procedure sp_ObtenerEspecialidades()
begin
	select code,nome,costo from tabesp;
end
&&
delimiter ;

call sp_ObtenerEspecialidades();


/*
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
*/

drop procedure if exists sp_ObtenerMedicoEspecialidad;
delimiter &&
create procedure sp_ObtenerMedicoEspecialidad(in p_codes char(3))
begin
	select codmed,Nombre,(year(now())-colegiatura) 
    from medicos
    where codes=p_codes;
end
&&
delimiter ;

call sp_ObtenerMedicoEspecialidad('E01');


/*
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
*/

drop procedure if exists sp_ObtenerCitasMedico;
delimiter &&
create procedure sp_ObtenerCitasMedico(in p_codmed char(4))
begin
	select nrocita,nompac,tipo,pago 
    from citas
    where codmed=p_codmed;
end
&&
delimiter ;

call sp_ObtenerCitasMedico('M002');

/*
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
*/

drop procedure if exists sp_ObtenerUltimaCita;
delimiter &&
create procedure sp_ObtenerUltimaCita()
begin
	select max(nrocita)+1,min(nrocita)
    from citas;
end
&&
delimiter ;

call sp_ObtenerUltimaCita();


/*
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
*/

drop procedure if exists sp_ObtenerPrecioxEspecialidadMedico;
delimiter &&
create procedure sp_ObtenerPrecioxEspecialidadMedico(in p_codmed char(4))
begin
	select t.code,t.costo
	from medicos m
	inner join tabesp t
	on m.codes=t.code
	where codmed=p_codmed;


end
&&
delimiter ;

call sp_ObtenerPrecioxEspecialidadMedico('M001');




