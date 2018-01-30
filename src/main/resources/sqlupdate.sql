update performance_emp pe
inner join (select max(date) as date,emp_id,per_id from perf_emp_confirmation group by emp_id,per_id) pec on pe.emp_id=pec.emp_id and pe.per_id=pec.per_id
inner join perf_emp_confirmation peb on pec.emp_id=peb.emp_id and pec.per_id=peb.per_id and pec.date = peb.date
set pe.confirm = confirmation, pe.confirm_date=peb.date


