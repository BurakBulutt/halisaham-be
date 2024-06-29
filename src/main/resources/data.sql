INSERT INTO public.city(id,created,modified,code,name) VALUES ('1',CURRENT_TIMESTAMP ,null,'TR-34','ISTANBUL') ON CONFLICT (id) DO NOTHING;

INSERT INTO public.district(id,created,modified,city_id,name) VALUES (1,CURRENT_TIMESTAMP ,null,'1','TUZLA') ON CONFLICT (id) DO NOTHING;

INSERT INTO public.street(id,created,modified,district_id,name) VALUES (1,CURRENT_TIMESTAMP ,null,'1','YAYLA') ON CONFLICT (id) DO NOTHING;
