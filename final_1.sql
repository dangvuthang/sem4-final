PGDMP                         x            sem4    12.2    12.2 c    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    143440    sem4    DATABASE     �   CREATE DATABASE sem4 WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.932' LC_CTYPE = 'English_United States.932';
    DROP DATABASE sem4;
                postgres    false            �            1259    143441    authentication_providers    TABLE     x   CREATE TABLE public.authentication_providers (
    provider_key character varying(255) NOT NULL,
    user_id integer
);
 ,   DROP TABLE public.authentication_providers;
       public         heap    postgres    false            �            1259    143444    bookings    TABLE     b  CREATE TABLE public.bookings (
    id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    is_cancelled boolean DEFAULT true NOT NULL,
    paid boolean,
    price numeric(19,2) NOT NULL,
    quantity integer NOT NULL,
    tour_id integer NOT NULL,
    user_id integer NOT NULL,
    deadline_cancelled timestamp without time zone
);
    DROP TABLE public.bookings;
       public         heap    postgres    false            �            1259    143448    bookings_id_seq    SEQUENCE     �   CREATE SEQUENCE public.bookings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.bookings_id_seq;
       public          postgres    false    203            �           0    0    bookings_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.bookings_id_seq OWNED BY public.bookings.id;
          public          postgres    false    204            �            1259    143450    guides    TABLE     �   CREATE TABLE public.guides (
    id integer NOT NULL,
    description character varying(255) NOT NULL,
    number_of_ratings integer NOT NULL,
    rating_average numeric(19,2) NOT NULL,
    user_id integer NOT NULL
);
    DROP TABLE public.guides;
       public         heap    postgres    false            �            1259    143453    guides_id_seq    SEQUENCE     �   CREATE SEQUENCE public.guides_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.guides_id_seq;
       public          postgres    false    205            �           0    0    guides_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.guides_id_seq OWNED BY public.guides.id;
          public          postgres    false    206            �            1259    143455 	   locations    TABLE       CREATE TABLE public.locations (
    id integer NOT NULL,
    address character varying(255) NOT NULL,
    is_active boolean DEFAULT true,
    latitude numeric(19,2) NOT NULL,
    longitude numeric(19,2) NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.locations;
       public         heap    postgres    false            �            1259    143462    locations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.locations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.locations_id_seq;
       public          postgres    false    207            �           0    0    locations_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.locations_id_seq OWNED BY public.locations.id;
          public          postgres    false    208            �            1259    143464    review_guides    TABLE     $  CREATE TABLE public.review_guides (
    id integer NOT NULL,
    active boolean DEFAULT true NOT NULL,
    created_at timestamp without time zone NOT NULL,
    rating integer NOT NULL,
    review character varying(255) NOT NULL,
    guide_id integer NOT NULL,
    user_id integer NOT NULL
);
 !   DROP TABLE public.review_guides;
       public         heap    postgres    false            �            1259    143468    review_guides_id_seq    SEQUENCE     �   CREATE SEQUENCE public.review_guides_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.review_guides_id_seq;
       public          postgres    false    209            �           0    0    review_guides_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.review_guides_id_seq OWNED BY public.review_guides.id;
          public          postgres    false    210            �            1259    143470    review_tours    TABLE     "  CREATE TABLE public.review_tours (
    id integer NOT NULL,
    active boolean DEFAULT true NOT NULL,
    created_at timestamp without time zone NOT NULL,
    rating integer NOT NULL,
    review character varying(255) NOT NULL,
    tour_id integer NOT NULL,
    user_id integer NOT NULL
);
     DROP TABLE public.review_tours;
       public         heap    postgres    false            �            1259    143474    review_tours_id_seq    SEQUENCE     �   CREATE SEQUENCE public.review_tours_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.review_tours_id_seq;
       public          postgres    false    211            �           0    0    review_tours_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.review_tours_id_seq OWNED BY public.review_tours.id;
          public          postgres    false    212            �            1259    143476    roles    TABLE     a   CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    143479    roles_id_seq    SEQUENCE     �   CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    213            �           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    214            �            1259    143481    tour_images    TABLE     �   CREATE TABLE public.tour_images (
    id integer NOT NULL,
    image_url character varying(255) NOT NULL,
    tour_id integer
);
    DROP TABLE public.tour_images;
       public         heap    postgres    false            �            1259    143484    tour_images_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tour_images_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.tour_images_id_seq;
       public          postgres    false    215            �           0    0    tour_images_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.tour_images_id_seq OWNED BY public.tour_images.id;
          public          postgres    false    216            �            1259    143486    tour_locations    TABLE     �   CREATE TABLE public.tour_locations (
    id integer NOT NULL,
    date timestamp without time zone NOT NULL,
    location_id integer NOT NULL,
    tour_id integer NOT NULL
);
 "   DROP TABLE public.tour_locations;
       public         heap    postgres    false            �            1259    143489    tour_locations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tour_locations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.tour_locations_id_seq;
       public          postgres    false    217            �           0    0    tour_locations_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.tour_locations_id_seq OWNED BY public.tour_locations.id;
          public          postgres    false    218            �            1259    143491 
   tour_types    TABLE     f   CREATE TABLE public.tour_types (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.tour_types;
       public         heap    postgres    false            �            1259    143494    tour_types_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tour_types_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.tour_types_id_seq;
       public          postgres    false    219            �           0    0    tour_types_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.tour_types_id_seq OWNED BY public.tour_types.id;
          public          postgres    false    220            �            1259    143496    tours    TABLE     f  CREATE TABLE public.tours (
    id integer NOT NULL,
    active boolean DEFAULT true NOT NULL,
    current_group_size integer NOT NULL,
    description text NOT NULL,
    duration integer NOT NULL,
    end_date date NOT NULL,
    max_group_size integer NOT NULL,
    name character varying(255) NOT NULL,
    number_of_ratings numeric(19,2),
    price numeric(19,2) NOT NULL,
    price_discount numeric(19,2),
    rating_average numeric(19,2),
    start_date date NOT NULL,
    summary character varying(255) NOT NULL,
    tour_image_cover character varying(255),
    guide_id integer,
    tour_type_id integer
);
    DROP TABLE public.tours;
       public         heap    postgres    false            �            1259    143503    tours_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tours_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.tours_id_seq;
       public          postgres    false    221            �           0    0    tours_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.tours_id_seq OWNED BY public.tours.id;
          public          postgres    false    222            �            1259    143505    users    TABLE     ;  CREATE TABLE public.users (
    id integer NOT NULL,
    active boolean DEFAULT true,
    avatar_image character varying(255),
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255),
    phone character varying(255),
    role_id integer NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    143512    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    223            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    224            �
           2604    143514    bookings id    DEFAULT     j   ALTER TABLE ONLY public.bookings ALTER COLUMN id SET DEFAULT nextval('public.bookings_id_seq'::regclass);
 :   ALTER TABLE public.bookings ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    204    203            �
           2604    143515 	   guides id    DEFAULT     f   ALTER TABLE ONLY public.guides ALTER COLUMN id SET DEFAULT nextval('public.guides_id_seq'::regclass);
 8   ALTER TABLE public.guides ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    205            �
           2604    143516    locations id    DEFAULT     l   ALTER TABLE ONLY public.locations ALTER COLUMN id SET DEFAULT nextval('public.locations_id_seq'::regclass);
 ;   ALTER TABLE public.locations ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    208    207            �
           2604    143517    review_guides id    DEFAULT     t   ALTER TABLE ONLY public.review_guides ALTER COLUMN id SET DEFAULT nextval('public.review_guides_id_seq'::regclass);
 ?   ALTER TABLE public.review_guides ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209            �
           2604    143518    review_tours id    DEFAULT     r   ALTER TABLE ONLY public.review_tours ALTER COLUMN id SET DEFAULT nextval('public.review_tours_id_seq'::regclass);
 >   ALTER TABLE public.review_tours ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211            �
           2604    143519    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    213            �
           2604    143520    tour_images id    DEFAULT     p   ALTER TABLE ONLY public.tour_images ALTER COLUMN id SET DEFAULT nextval('public.tour_images_id_seq'::regclass);
 =   ALTER TABLE public.tour_images ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215            �
           2604    143521    tour_locations id    DEFAULT     v   ALTER TABLE ONLY public.tour_locations ALTER COLUMN id SET DEFAULT nextval('public.tour_locations_id_seq'::regclass);
 @   ALTER TABLE public.tour_locations ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217            �
           2604    143522    tour_types id    DEFAULT     n   ALTER TABLE ONLY public.tour_types ALTER COLUMN id SET DEFAULT nextval('public.tour_types_id_seq'::regclass);
 <   ALTER TABLE public.tour_types ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219            �
           2604    143523    tours id    DEFAULT     d   ALTER TABLE ONLY public.tours ALTER COLUMN id SET DEFAULT nextval('public.tours_id_seq'::regclass);
 7   ALTER TABLE public.tours ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221            �
           2604    143524    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223            y          0    143441    authentication_providers 
   TABLE DATA           I   COPY public.authentication_providers (provider_key, user_id) FROM stdin;
    public          postgres    false    202   �v       z          0    143444    bookings 
   TABLE DATA           }   COPY public.bookings (id, created_at, is_cancelled, paid, price, quantity, tour_id, user_id, deadline_cancelled) FROM stdin;
    public          postgres    false    203   �v       |          0    143450    guides 
   TABLE DATA           ]   COPY public.guides (id, description, number_of_ratings, rating_average, user_id) FROM stdin;
    public          postgres    false    205   �w       ~          0    143455 	   locations 
   TABLE DATA           V   COPY public.locations (id, address, is_active, latitude, longitude, name) FROM stdin;
    public          postgres    false    207   ?x       �          0    143464    review_guides 
   TABLE DATA           b   COPY public.review_guides (id, active, created_at, rating, review, guide_id, user_id) FROM stdin;
    public          postgres    false    209   mz       �          0    143470    review_tours 
   TABLE DATA           `   COPY public.review_tours (id, active, created_at, rating, review, tour_id, user_id) FROM stdin;
    public          postgres    false    211   �z       �          0    143476    roles 
   TABLE DATA           )   COPY public.roles (id, name) FROM stdin;
    public          postgres    false    213   �{       �          0    143481    tour_images 
   TABLE DATA           =   COPY public.tour_images (id, image_url, tour_id) FROM stdin;
    public          postgres    false    215   �{       �          0    143486    tour_locations 
   TABLE DATA           H   COPY public.tour_locations (id, date, location_id, tour_id) FROM stdin;
    public          postgres    false    217           �          0    143491 
   tour_types 
   TABLE DATA           .   COPY public.tour_types (id, name) FROM stdin;
    public          postgres    false    219   ��       �          0    143496    tours 
   TABLE DATA           �   COPY public.tours (id, active, current_group_size, description, duration, end_date, max_group_size, name, number_of_ratings, price, price_discount, rating_average, start_date, summary, tour_image_cover, guide_id, tour_type_id) FROM stdin;
    public          postgres    false    221   ۀ       �          0    143505    users 
   TABLE DATA           `   COPY public.users (id, active, avatar_image, email, name, password, phone, role_id) FROM stdin;
    public          postgres    false    223   �       �           0    0    bookings_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.bookings_id_seq', 15, true);
          public          postgres    false    204            �           0    0    guides_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.guides_id_seq', 7, true);
          public          postgres    false    206            �           0    0    locations_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.locations_id_seq', 30, true);
          public          postgres    false    208            �           0    0    review_guides_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.review_guides_id_seq', 9, true);
          public          postgres    false    210            �           0    0    review_tours_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.review_tours_id_seq', 9, true);
          public          postgres    false    212            �           0    0    roles_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.roles_id_seq', 3, true);
          public          postgres    false    214            �           0    0    tour_images_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.tour_images_id_seq', 150, true);
          public          postgres    false    216            �           0    0    tour_locations_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.tour_locations_id_seq', 62, true);
          public          postgres    false    218            �           0    0    tour_types_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.tour_types_id_seq', 6, true);
          public          postgres    false    220            �           0    0    tours_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.tours_id_seq', 30, true);
          public          postgres    false    222            �           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 13, true);
          public          postgres    false    224            �
           2606    143526 6   authentication_providers authentication_providers_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public.authentication_providers
    ADD CONSTRAINT authentication_providers_pkey PRIMARY KEY (provider_key);
 `   ALTER TABLE ONLY public.authentication_providers DROP CONSTRAINT authentication_providers_pkey;
       public            postgres    false    202            �
           2606    143528    bookings bookings_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.bookings DROP CONSTRAINT bookings_pkey;
       public            postgres    false    203            �
           2606    143530    guides guides_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.guides
    ADD CONSTRAINT guides_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.guides DROP CONSTRAINT guides_pkey;
       public            postgres    false    205            �
           2606    143532    locations locations_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.locations DROP CONSTRAINT locations_pkey;
       public            postgres    false    207            �
           2606    143534     review_guides review_guides_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.review_guides
    ADD CONSTRAINT review_guides_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.review_guides DROP CONSTRAINT review_guides_pkey;
       public            postgres    false    209            �
           2606    143536    review_tours review_tours_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.review_tours
    ADD CONSTRAINT review_tours_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.review_tours DROP CONSTRAINT review_tours_pkey;
       public            postgres    false    211            �
           2606    143538    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    213            �
           2606    143540    tour_images tour_images_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.tour_images
    ADD CONSTRAINT tour_images_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.tour_images DROP CONSTRAINT tour_images_pkey;
       public            postgres    false    215            �
           2606    143542 "   tour_locations tour_locations_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.tour_locations
    ADD CONSTRAINT tour_locations_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.tour_locations DROP CONSTRAINT tour_locations_pkey;
       public            postgres    false    217            �
           2606    143544    tour_types tour_types_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.tour_types
    ADD CONSTRAINT tour_types_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.tour_types DROP CONSTRAINT tour_types_pkey;
       public            postgres    false    219            �
           2606    143546    tours tours_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.tours
    ADD CONSTRAINT tours_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.tours DROP CONSTRAINT tours_pkey;
       public            postgres    false    221            �
           2606    143548 !   users uk6dotkott2kjsp8vw4d0m25fb7 
   CONSTRAINT     ]   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7;
       public            postgres    false    223            �
           2606    143550    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    223            �
           2606    143551 (   review_tours fk39x9tgriieqm3jgkoex33wpos    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_tours
    ADD CONSTRAINT fk39x9tgriieqm3jgkoex33wpos FOREIGN KEY (tour_id) REFERENCES public.tours(id);
 R   ALTER TABLE ONLY public.review_tours DROP CONSTRAINT fk39x9tgriieqm3jgkoex33wpos;
       public          postgres    false    221    2792    211            �
           2606    143556 !   tours fk3ar2uw482wtyadxn46ypqxjyk    FK CONSTRAINT     �   ALTER TABLE ONLY public.tours
    ADD CONSTRAINT fk3ar2uw482wtyadxn46ypqxjyk FOREIGN KEY (guide_id) REFERENCES public.guides(id);
 K   ALTER TABLE ONLY public.tours DROP CONSTRAINT fk3ar2uw482wtyadxn46ypqxjyk;
       public          postgres    false    205    2776    221            �
           2606    143561 )   review_guides fk4456ig5g72fd8la91i6gg4282    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_guides
    ADD CONSTRAINT fk4456ig5g72fd8la91i6gg4282 FOREIGN KEY (guide_id) REFERENCES public.guides(id);
 S   ALTER TABLE ONLY public.review_guides DROP CONSTRAINT fk4456ig5g72fd8la91i6gg4282;
       public          postgres    false    209    2776    205            �
           2606    143566 (   review_tours fk57rgbn2swlpquggy9wwgsc6ew    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_tours
    ADD CONSTRAINT fk57rgbn2swlpquggy9wwgsc6ew FOREIGN KEY (user_id) REFERENCES public.users(id);
 R   ALTER TABLE ONLY public.review_tours DROP CONSTRAINT fk57rgbn2swlpquggy9wwgsc6ew;
       public          postgres    false    211    223    2796            �
           2606    143571 *   tour_locations fk5rii550hmj37axh535de91g4u    FK CONSTRAINT     �   ALTER TABLE ONLY public.tour_locations
    ADD CONSTRAINT fk5rii550hmj37axh535de91g4u FOREIGN KEY (tour_id) REFERENCES public.tours(id);
 T   ALTER TABLE ONLY public.tour_locations DROP CONSTRAINT fk5rii550hmj37axh535de91g4u;
       public          postgres    false    2792    217    221            �
           2606    143576 !   tours fk954q05c5yddloovsg149elqug    FK CONSTRAINT     �   ALTER TABLE ONLY public.tours
    ADD CONSTRAINT fk954q05c5yddloovsg149elqug FOREIGN KEY (tour_type_id) REFERENCES public.tour_types(id);
 K   ALTER TABLE ONLY public.tours DROP CONSTRAINT fk954q05c5yddloovsg149elqug;
       public          postgres    false    221    219    2790            �
           2606    143581 "   guides fk9epk7rkhp13yrdhou2838dgam    FK CONSTRAINT     �   ALTER TABLE ONLY public.guides
    ADD CONSTRAINT fk9epk7rkhp13yrdhou2838dgam FOREIGN KEY (user_id) REFERENCES public.users(id);
 L   ALTER TABLE ONLY public.guides DROP CONSTRAINT fk9epk7rkhp13yrdhou2838dgam;
       public          postgres    false    2796    205    223            �
           2606    143586 $   bookings fkeyog2oic85xg7hsu2je2lx3s6    FK CONSTRAINT     �   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT fkeyog2oic85xg7hsu2je2lx3s6 FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.bookings DROP CONSTRAINT fkeyog2oic85xg7hsu2je2lx3s6;
       public          postgres    false    2796    223    203            �
           2606    143591 *   tour_locations fkg5q7j10p4myy25muaasfrbvl5    FK CONSTRAINT     �   ALTER TABLE ONLY public.tour_locations
    ADD CONSTRAINT fkg5q7j10p4myy25muaasfrbvl5 FOREIGN KEY (location_id) REFERENCES public.locations(id);
 T   ALTER TABLE ONLY public.tour_locations DROP CONSTRAINT fkg5q7j10p4myy25muaasfrbvl5;
       public          postgres    false    207    217    2778            �
           2606    143596 $   bookings fki21lisuytk5t7tlp7lv51ny2l    FK CONSTRAINT     �   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT fki21lisuytk5t7tlp7lv51ny2l FOREIGN KEY (tour_id) REFERENCES public.tours(id);
 N   ALTER TABLE ONLY public.bookings DROP CONSTRAINT fki21lisuytk5t7tlp7lv51ny2l;
       public          postgres    false    2792    203    221            �
           2606    143601 )   review_guides fkmg7l95jlhpxcuxu117ibn9pdq    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_guides
    ADD CONSTRAINT fkmg7l95jlhpxcuxu117ibn9pdq FOREIGN KEY (user_id) REFERENCES public.users(id);
 S   ALTER TABLE ONLY public.review_guides DROP CONSTRAINT fkmg7l95jlhpxcuxu117ibn9pdq;
       public          postgres    false    223    209    2796            �
           2606    143606 4   authentication_providers fkp33do4hsg1f15cdw1popaxbhn    FK CONSTRAINT     �   ALTER TABLE ONLY public.authentication_providers
    ADD CONSTRAINT fkp33do4hsg1f15cdw1popaxbhn FOREIGN KEY (user_id) REFERENCES public.users(id);
 ^   ALTER TABLE ONLY public.authentication_providers DROP CONSTRAINT fkp33do4hsg1f15cdw1popaxbhn;
       public          postgres    false    202    223    2796            �
           2606    143611 !   users fkp56c1712k691lhsyewcssf40f    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkp56c1712k691lhsyewcssf40f FOREIGN KEY (role_id) REFERENCES public.roles(id);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT fkp56c1712k691lhsyewcssf40f;
       public          postgres    false    2784    223    213            �
           2606    143616 '   tour_images fkth1m2rd6q6ltp8kii2msvfi5d    FK CONSTRAINT     �   ALTER TABLE ONLY public.tour_images
    ADD CONSTRAINT fkth1m2rd6q6ltp8kii2msvfi5d FOREIGN KEY (tour_id) REFERENCES public.tours(id);
 Q   ALTER TABLE ONLY public.tour_images DROP CONSTRAINT fkth1m2rd6q6ltp8kii2msvfi5d;
       public          postgres    false    221    2792    215            y   -   x��1�I��0�,K	K2s.�uO�.p�4N��)7�44����� �3
�      z   �   x�����0�3��8l7Ct��3A�Wl��^��9���Fƌ��������/͇p~R	Q���(O*>%�lZ�j�=D�@wi1�[�;"5~� ����ЖI��])C�����j��ť���jP][�l[wſ+?�u�-�t`!j�      |   �   x���=�0��9>�OP�����X"�i+B�\�����B$���,��E%D�6���؉�LJ+�i�J(�ɌB�FP:�!�t6=	��-�
����!<<���YC��4��\�:�l��媘���!~���E�L�<��]�$s�׳c��/��D>�. �3�SI      ~     x�e�ˎ�0E��W��%/�����`�n�ԨL�"c/���D?:�&o�KRT��&^�S���Li�S�������#hv:���B'bDa�ϔߺ��Nc�(Q���v�`��	�4e�&��q�|פ[�]ډ��	��_#*�r����&����k�$�i-�-�h���S�3xaC)�eF1O�y�v�H*S�*�O*vD�C�F�%�~�|���'~*=��r'��!(��?�v���)�`P��,yoȊ�
/�Y0`6�x�ه/5��)�d�Nݦ?�.��V���zʭTGJʳ�cOF
�ܾ�%��I����=?��$���R��U�?w}�
��^�a��>���@3�y 6�����{��Vl�j~�h��8P5�V��;̛P��*n�8�	��lxF�
OJ�>A��ߚrFȪ\~fxO�~�1��K��/=�����(aiy�st(�z����ՊA��"�:j�!�2�j㠳��&�k�	�<�m�|:�/ #�5�	¹�Ň7c�! �/V)      �   �   x���;
�@D��^@�&�KOa�"�l����& �|̛����R*S���}�#�3��8B6�:��77�^I�b����ø�����F��da������^�YGUvh̊����Vm~�TT?d�x`BT%      �   �   x���=�0Fg�� ����tcaeTe!����+3�,��<>���S&����8��y��;���o�ɓ��4�y���k�8&�s�q�(
n�.�Pgk:�8�ؙ_�q�`�(�A��˃�[˃�9=��:EQa      �   $   x�3�tL����2�t/�LI�2�-N-����� k��      �   +  x�]��n�6�k�0+<3���Q7�&�vsT�Ro�,=�j�4T��w�����|?�`�1�y���k�.߆��q�|����x�^�B�P����n�n�o��!����.IR�R�Ծ ߜ�}~{/׷�a�{G�aOdl @� @
]��2]�y9,95�ᙒ(�F(�)���u�m������y�wx}��� 4�FLhJd�J�����)ͧ��2�ͯ�]��oi� r����i�A�~�Qjl������2ԏ7:��dH.q+AK�J�2l%i���t����a���X"�1U�}�(Hm��ȷ����r���I�@ił*�B*�TH�kn�C^n��o��Q�Q�K�^UB+B&�>x��V���Dux&T#RB��DJݺ@��X5@���:>��	��I!��9�Z�����\͵c*�}AT�mA2��x���SpD�*�)�QP"��yx?6�\�;�~��T`y/�*�!��k^N�s5tt	����P��.C�`Ff����)���\��ڃ))�Q��J}�DF]���� ���z�$0�EPŷ����8]�z;�� ,�.�bQ(JE�h(JE�oշ������h�USQ(�ES���<�e���u��[�-�#�Ͻ��q�@	�`	�!���]��j�|��(�]WKjP�WC3hը�o�ߟ�K`�]��J(�H!2��[�y�^u�� �=VC5(FjX,�Q1��ɟ���أ�6Z1	j��z��S���������p���9�c�m�?DP"�a��JĿ���Ǉ�9���Y�VKjP�WC3>��H��Oι�����      �   �  x�u��m1D�=U���ƯԒ���lq%��<�a�Q�r��h}���U���0�'Rˉ8�c�j��h]�9Ze��A���A����@r�}q��@��<ٴ@3���$��î�O��-p����ɩ�\vi�[
_�`IM0A�V
P=e���!����	D��v�ro#�n��U��v�I��*���U����r�Z��>���Y阺���^���y�!��iɓ���Oă7b�5w�sQKd��[>d��rH�C�!�$9�f� ���gX>�w��{��H���y,R��ëݧ��7KV����=�r����o��5��N*�ږב4�yB�����Y-�3Xh�A�����@� J���ǫ!c���}NPu~�������      �   /   x�3�J�I���K�2��L�()NM�9S�R�JJ�R�b���� !��      �      x��Z�r�F]�_�5�8��X$�^�GK���ē��4�&��FЀh����� h+�lfa�$�q�>�=�6G�h>��>s/�QZe��*�ؕ�tQl�7��j�=��]as�U��-��Uce��4��?���m��]c�l�^lQpZ��EW�t�6~������`zz0����Z�[ݪ'�5��d:�ߙ��?i����������W�ڨ���ƭ]�ٺ��j�b
�mnT�/GI9y�K�yY��V�S2-�����q^�o3$x��u�h��V����4{s$���z۪[>W7X֨�*��R�.n+�+Ԣ�t��UM0Q�[��YP�/a�p�aͱw��Z��{���rW�5U�
�1��u����[=P>�9��� Iѽ��2�P���B7jc̳�^�Ϡ�l\�n��ƻ�4.{���4֫��Tjat�>�n^�)��tTl�Ժ����+C�+p(�+~2���S���9�=]@7/T�k�*;ڨk���ѡ�f�Z�=��t�(*��.T��g���t<�N�(7�����1D�����{��[�+;r��NF�Qgg����4̅��F��Pϥ�&�7?�J�g���c��MA/��2M<�G��r���x���Z��O)
 ���+����1�UB���o���n������*lMMGl�XJ5�}��!�UW�]cD���ux���MlG��*�Q��r`�{x3�����Uy���k��O�������t	v�Dr���0�+o՝�Ŧ�#�|�a�7PN�;�
ӕ�YN�Y����V]iH��9�E(~Vؒ.a���"� �p7z�i�p��6�Q�j�V�N�;->�����̩h>k�B��JC�+A������L�|�q$uC4��_�?i;{�_�K;��5gb��������wl&���Vvbw:>`��4!R81[�"��q]��v���o�X�p��q����Lx,Q����G��q��Sc����Q�ǯ䜙�C
�^��X��Ϯ)r��1�+K L��}_�F�F��zE��1�K���TV"|��2�vi&H��+���nt�b||����t�k�;�B�n��겱�ʌï��?�'��5���qM>yP�1��.m8B�2>�U�j�`�f� �h�%,I�4H!��Q��A8��������f��Ⰷ'�m�i�o���EpRW�&��b�-�A2v5u��3�jM[�r�De~O_�@:,5�y���;'n��э��������dZֻ�HzdΟ�H���,v4:|3;����.�g������x���BHL�x]I�+ߙgZ��-�2�U�� 6�\��x3��}��讵KH����!��~�?\T���A�\�w�	��z�8���i��=��jZy"�Wb��y U�(`c����2Q�%.��LI��gE�(���X�b�+��'۲pZX	�2-����a�s:�v�4����gB]�&�h'�ľ���p��s����&�B��M�l�m���t�Ի}ybb��M�1�e4��h�?��|�/��W(��N��4�	�u��)Ո�}"j�>�t�Xr.�9z���˔�Jm@���/�}R�m�o�7�@��e��z�O�`���0!v���\�&�t��[�U����#}�ۊ֙X� .����B��U�s��H��4�H�$����a�U2�*M�,6oU�/p-�AD��hfg�Ix/\g;��̀1�θ$�	�[VZA�	ΰg�c&�S1�ǰ0x�t�@�VMG�t�V�k��&��(Ƙ�`����y�ʃ�{
�!�>���Vq��L�EG�F��v���I�Q�Ud9�ᩪ� �0	�'�4��"<|��$��s*�?r_mmI-����8�3�u;v��D�G=ZJ����CI���R,�'"(�ܷ��h�>L�Щ�"p[X�B"�������n�
Յ]H%!�!L���s92U,=˔%������9z�p���-��3�}`���5�ϡ6��=���(&u��#�PzZ��5�z�4�{=�k��Uui �憁�ʲy�n#�qU��k���ޒғ�_w�H��°�anb�{��+���20��05Q��7E!�[��a�6�=p���wz�!(DI� oZ������UA3|��Ql)��V����<(��{�ܻ�Y
��� �,�Nv����śs�Q|�D��v'XwH�.�A
�ea�Xf�:&�	�'������ށ0��gMP
�����b�K|�ʖ�M�]ײ�fQsc��AZ�2��rag����v�m'ߔe��咥�9�P�U7�F湡6��Pf+-�� �*�$;"�<i
E��[Xv(�2<X ��rl�j3� ���"��pW/-�j��X\1��u�������SWFj�r����k�$�w��`X~�Px��w��O9[���G�T����:�Q���	�b�.P� Adr?����O	 ǻ����F�6(�Oz\o���*�c��S��.'����p9�D�{'�f;l�}�-ɮG��1�p�J�p-$N�J�;K:PMq����F���c�
˃��u�E,���y4����(ڂ��,����Wd�8} ����,�tZ�!���n�ŀ�vL4�e,��6�(����r�����qh�(�|)u?x��H��'�բ�4%xݸL�Ǖ�m�"����x�n�����y׌�I�B�UE+J;F��`w��#�28�+����T:�ɉ�p�����Ѷ�,:d��i7����t �Ea=|�	�k�B�M�iu�Yuoz*��.#��^%D�H������'ج�Ѻ� KD��ڵ�؅�jV�yj�D���_�k���.���Oz��Kk�B�NP\�]��W�d"��[��U��)�Z�xl�D6Ⱥ��q ������A�]���O,_=�a@Y�G)�E;��>m9�&{@�b��p-z�^��*͏��UC���x�$��ͤB�0k"�8y�/�$78�+�����ҝɶ�?�X�� �&|J*!\&Z��NCWZr�����O~��K��V4�tM�����$^u�1:G��}yr�t��ڕ���p�,��#�$F}{�Dv3j����gI�Y�b���$�e`����j!�R�AWa��gb9典CfM����g��|Ѭ8)&�e���P�d:I�θc����f�a�\��˥.�n$�YӮ��6�Vŷ�!��@4�z�o��~+-�t�ˤ+.:	�7$�F
���`�h���8m4��1�
Ud�8�Q���m�p�F�����lOÂY�,��Âj+�q,�jW�Ѩ�C,C8���c!��U���GGg��)v�~�
��5
��}<�;
AQ�J:�.�
K����.�/֩; hC�xi#>�K�7�"��i�R��h��K6$�m�+������NR .D�KR0.��J���}�����	Ň�CB� ���f$F��&��lз�M��$���̱��;4�IY_Ђ}�.{F��;�M�F?f��x��.�+ޥ��ѡ^�.Q�2ҵ�!��,�J�/�y�{�F�ve5�"o��]�� �0y�3hRBcV̳���]��}����<�#n�3(m�i���5Ҽ��	ʤs.��1��E�}��PN�3va�?%��sn�N��m?����!E�;K�V;f��M�V�2t����ܨ�6�4U���H�pm�~XeL�wA �5���,�o���Z� &!��.M�Mg2����w+�]��"@e+���#)�݃��]=�v�B^�[�Ae�x�(�~ւ��f�m����Wk^���������Q�Z�+68E!����������OF�7���.���w%��80p[9f��Mw^Y8���a�-��E�ʹ�~�$��� �HF�Ĩ�����j��1���n����o|�\1��S�>��On�5�\����὆W�K
�q�:�C�?�;w]d�����! �j�[sN��`����=�J�\���=�Mg���?����MG�//!d�(I�nB;(�=�_]`��V�U��/p��5�oR�*4޶� �  -��"5*�ޯ�-�p��`^Ruf�����Y�4?�*�{^wU�̏7�RQ�- OhS�.	;NU��y�ި��0(/P���칶�	�&x��}],��$x����f^�����<��cc%gzj�)vzF�/��@ؔ^}��H�*ٯ޺ح�W`��@�X�%��a�%/�q��u�:t�����+e���y��a/5�H1��/H��5�e�g��Ա�3Y���&LJ�bX�ܠ�� %;iRrɋ��n�C� ��zT�rLj�׮���u=��9&�	|W��Y�U�|�#�Nߒ���yg���=<tO�ϿC���^:[�y���,=N�����6��vJ��;ؑA��)��F�LD�;Cѿj����.ߘޤ�k6h0��;���M������:�!T$Fjk��.�rv�>|�a��\I���^�fL��������<di9���]S;��J�jH���\�{���O� 7���3Vih��m2	�b�}���ɛ7o���:�      �   �  x�e�ݎ�0���S���(`���j�v+��ݢ�ZE�(a�[bP����w��.AH�<�3�81D3���o���;6��צ�Ư�c�N�������|(������<ȋk����?��j{�q{+²>l�{U���a�-doz���#+���Ȳ0	���q0~s���&���YZ��&LSKz��xK�|��B�۞�����o�c�:��gz�!�"����0�H�hs���ZP9����_qx5e���������@+�H�}��W�*Z��ti�f"�@f@v�3��װ�J�	w���Q5vBQ]�*�BP�R  �R-'rRF:l	!4X��$p�%��½��f�����x��b.
�/��k0kq㘋3s�a�\6}��Z֜�W8Z�V�W����L��W�Epv��p�J��;D7\�If���<��+     