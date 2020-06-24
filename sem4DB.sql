PGDMP     9    
                x            sem4    12.3    12.3 c    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17167    sem4    DATABASE     �   CREATE DATABASE sem4 WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.936' LC_CTYPE = 'English_United States.936';
    DROP DATABASE sem4;
                postgres    false            �            1259    17348    authentication_providers    TABLE     x   CREATE TABLE public.authentication_providers (
    provider_key character varying(255) NOT NULL,
    user_id integer
);
 ,   DROP TABLE public.authentication_providers;
       public         heap    postgres    false            �            1259    17351    bookings    TABLE     V  CREATE TABLE public.bookings (
    id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    is_cancelled boolean DEFAULT true NOT NULL,
    paid boolean,
    price numeric(19,2) NOT NULL,
    quantity integer NOT NULL,
    tour_id integer NOT NULL,
    user_id integer NOT NULL,
    start_date date,
    end_date date
);
    DROP TABLE public.bookings;
       public         heap    postgres    false            �            1259    17355    bookings_id_seq    SEQUENCE     �   CREATE SEQUENCE public.bookings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.bookings_id_seq;
       public          postgres    false    203            �           0    0    bookings_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.bookings_id_seq OWNED BY public.bookings.id;
          public          postgres    false    204            �            1259    17357    guides    TABLE     �   CREATE TABLE public.guides (
    id integer NOT NULL,
    description text NOT NULL,
    number_of_ratings integer NOT NULL,
    rating_average numeric(19,2) NOT NULL,
    user_id integer NOT NULL
);
    DROP TABLE public.guides;
       public         heap    postgres    false            �            1259    17360    guides_id_seq    SEQUENCE     �   CREATE SEQUENCE public.guides_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.guides_id_seq;
       public          postgres    false    205            �           0    0    guides_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.guides_id_seq OWNED BY public.guides.id;
          public          postgres    false    206            �            1259    17362 	   locations    TABLE       CREATE TABLE public.locations (
    id integer NOT NULL,
    address character varying(255) NOT NULL,
    is_active boolean DEFAULT true,
    latitude numeric(19,2) NOT NULL,
    longitude numeric(19,2) NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.locations;
       public         heap    postgres    false            �            1259    17369    locations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.locations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.locations_id_seq;
       public          postgres    false    207            �           0    0    locations_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.locations_id_seq OWNED BY public.locations.id;
          public          postgres    false    208            �            1259    17371    review_guides    TABLE     $  CREATE TABLE public.review_guides (
    id integer NOT NULL,
    active boolean DEFAULT true NOT NULL,
    created_at timestamp without time zone NOT NULL,
    rating integer NOT NULL,
    review character varying(255) NOT NULL,
    guide_id integer NOT NULL,
    user_id integer NOT NULL
);
 !   DROP TABLE public.review_guides;
       public         heap    postgres    false            �            1259    17375    review_guides_id_seq    SEQUENCE     �   CREATE SEQUENCE public.review_guides_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.review_guides_id_seq;
       public          postgres    false    209            �           0    0    review_guides_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.review_guides_id_seq OWNED BY public.review_guides.id;
          public          postgres    false    210            �            1259    17377    review_tours    TABLE     "  CREATE TABLE public.review_tours (
    id integer NOT NULL,
    active boolean DEFAULT true NOT NULL,
    created_at timestamp without time zone NOT NULL,
    rating integer NOT NULL,
    review character varying(255) NOT NULL,
    tour_id integer NOT NULL,
    user_id integer NOT NULL
);
     DROP TABLE public.review_tours;
       public         heap    postgres    false            �            1259    17381    review_tours_id_seq    SEQUENCE     �   CREATE SEQUENCE public.review_tours_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.review_tours_id_seq;
       public          postgres    false    211            �           0    0    review_tours_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.review_tours_id_seq OWNED BY public.review_tours.id;
          public          postgres    false    212            �            1259    17383    roles    TABLE     a   CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    17386    roles_id_seq    SEQUENCE     �   CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    213            �           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    214            �            1259    17388    tour_images    TABLE     �   CREATE TABLE public.tour_images (
    id integer NOT NULL,
    image_url character varying(255) NOT NULL,
    tour_id integer
);
    DROP TABLE public.tour_images;
       public         heap    postgres    false            �            1259    17391    tour_images_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tour_images_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.tour_images_id_seq;
       public          postgres    false    215            �           0    0    tour_images_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.tour_images_id_seq OWNED BY public.tour_images.id;
          public          postgres    false    216            �            1259    17393    tour_locations    TABLE     �   CREATE TABLE public.tour_locations (
    id integer NOT NULL,
    date timestamp without time zone NOT NULL,
    location_id integer NOT NULL,
    tour_id integer NOT NULL
);
 "   DROP TABLE public.tour_locations;
       public         heap    postgres    false            �            1259    17396    tour_locations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tour_locations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.tour_locations_id_seq;
       public          postgres    false    217            �           0    0    tour_locations_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.tour_locations_id_seq OWNED BY public.tour_locations.id;
          public          postgres    false    218            �            1259    17398 
   tour_types    TABLE     f   CREATE TABLE public.tour_types (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.tour_types;
       public         heap    postgres    false            �            1259    17401    tour_types_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tour_types_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.tour_types_id_seq;
       public          postgres    false    219            �           0    0    tour_types_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.tour_types_id_seq OWNED BY public.tour_types.id;
          public          postgres    false    220            �            1259    17403    tours    TABLE     f  CREATE TABLE public.tours (
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
       public         heap    postgres    false            �            1259    17410    tours_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tours_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.tours_id_seq;
       public          postgres    false    221            �           0    0    tours_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.tours_id_seq OWNED BY public.tours.id;
          public          postgres    false    222            �            1259    17412    users    TABLE     ;  CREATE TABLE public.users (
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
       public         heap    postgres    false            �            1259    17419    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    223            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    224            �
           2604    17421    bookings id    DEFAULT     j   ALTER TABLE ONLY public.bookings ALTER COLUMN id SET DEFAULT nextval('public.bookings_id_seq'::regclass);
 :   ALTER TABLE public.bookings ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    204    203            �
           2604    17422 	   guides id    DEFAULT     f   ALTER TABLE ONLY public.guides ALTER COLUMN id SET DEFAULT nextval('public.guides_id_seq'::regclass);
 8   ALTER TABLE public.guides ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    205            �
           2604    17423    locations id    DEFAULT     l   ALTER TABLE ONLY public.locations ALTER COLUMN id SET DEFAULT nextval('public.locations_id_seq'::regclass);
 ;   ALTER TABLE public.locations ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    208    207            �
           2604    17424    review_guides id    DEFAULT     t   ALTER TABLE ONLY public.review_guides ALTER COLUMN id SET DEFAULT nextval('public.review_guides_id_seq'::regclass);
 ?   ALTER TABLE public.review_guides ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209            �
           2604    17425    review_tours id    DEFAULT     r   ALTER TABLE ONLY public.review_tours ALTER COLUMN id SET DEFAULT nextval('public.review_tours_id_seq'::regclass);
 >   ALTER TABLE public.review_tours ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211            �
           2604    17426    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    213            �
           2604    17427    tour_images id    DEFAULT     p   ALTER TABLE ONLY public.tour_images ALTER COLUMN id SET DEFAULT nextval('public.tour_images_id_seq'::regclass);
 =   ALTER TABLE public.tour_images ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215            �
           2604    17428    tour_locations id    DEFAULT     v   ALTER TABLE ONLY public.tour_locations ALTER COLUMN id SET DEFAULT nextval('public.tour_locations_id_seq'::regclass);
 @   ALTER TABLE public.tour_locations ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217            �
           2604    17429    tour_types id    DEFAULT     n   ALTER TABLE ONLY public.tour_types ALTER COLUMN id SET DEFAULT nextval('public.tour_types_id_seq'::regclass);
 <   ALTER TABLE public.tour_types ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219            �
           2604    17430    tours id    DEFAULT     d   ALTER TABLE ONLY public.tours ALTER COLUMN id SET DEFAULT nextval('public.tours_id_seq'::regclass);
 7   ALTER TABLE public.tours ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221            �
           2604    17431    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223            z          0    17348    authentication_providers 
   TABLE DATA           I   COPY public.authentication_providers (provider_key, user_id) FROM stdin;
    public          postgres    false    202   Iv       {          0    17351    bookings 
   TABLE DATA              COPY public.bookings (id, created_at, is_cancelled, paid, price, quantity, tour_id, user_id, start_date, end_date) FROM stdin;
    public          postgres    false    203   �v       }          0    17357    guides 
   TABLE DATA           ]   COPY public.guides (id, description, number_of_ratings, rating_average, user_id) FROM stdin;
    public          postgres    false    205   ,x                 0    17362 	   locations 
   TABLE DATA           V   COPY public.locations (id, address, is_active, latitude, longitude, name) FROM stdin;
    public          postgres    false    207   �|       �          0    17371    review_guides 
   TABLE DATA           b   COPY public.review_guides (id, active, created_at, rating, review, guide_id, user_id) FROM stdin;
    public          postgres    false    209   �~       �          0    17377    review_tours 
   TABLE DATA           `   COPY public.review_tours (id, active, created_at, rating, review, tour_id, user_id) FROM stdin;
    public          postgres    false    211    �       �          0    17383    roles 
   TABLE DATA           )   COPY public.roles (id, name) FROM stdin;
    public          postgres    false    213   3�       �          0    17388    tour_images 
   TABLE DATA           =   COPY public.tour_images (id, image_url, tour_id) FROM stdin;
    public          postgres    false    215   g�       �          0    17393    tour_locations 
   TABLE DATA           H   COPY public.tour_locations (id, date, location_id, tour_id) FROM stdin;
    public          postgres    false    217   ��       �          0    17398 
   tour_types 
   TABLE DATA           .   COPY public.tour_types (id, name) FROM stdin;
    public          postgres    false    219   >�       �          0    17403    tours 
   TABLE DATA           �   COPY public.tours (id, active, current_group_size, description, duration, end_date, max_group_size, name, number_of_ratings, price, price_discount, rating_average, start_date, summary, tour_image_cover, guide_id, tour_type_id) FROM stdin;
    public          postgres    false    221   }�       �          0    17412    users 
   TABLE DATA           `   COPY public.users (id, active, avatar_image, email, name, password, phone, role_id) FROM stdin;
    public          postgres    false    223   ֘       �           0    0    bookings_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.bookings_id_seq', 37, true);
          public          postgres    false    204            �           0    0    guides_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.guides_id_seq', 7, true);
          public          postgres    false    206            �           0    0    locations_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.locations_id_seq', 30, true);
          public          postgres    false    208            �           0    0    review_guides_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.review_guides_id_seq', 70, true);
          public          postgres    false    210            �           0    0    review_tours_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.review_tours_id_seq', 45, true);
          public          postgres    false    212            �           0    0    roles_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.roles_id_seq', 3, true);
          public          postgres    false    214            �           0    0    tour_images_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.tour_images_id_seq', 150, true);
          public          postgres    false    216            �           0    0    tour_locations_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.tour_locations_id_seq', 62, true);
          public          postgres    false    218            �           0    0    tour_types_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.tour_types_id_seq', 6, true);
          public          postgres    false    220            �           0    0    tours_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.tours_id_seq', 30, true);
          public          postgres    false    222            �           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 13, true);
          public          postgres    false    224            �
           2606    17433 6   authentication_providers authentication_providers_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public.authentication_providers
    ADD CONSTRAINT authentication_providers_pkey PRIMARY KEY (provider_key);
 `   ALTER TABLE ONLY public.authentication_providers DROP CONSTRAINT authentication_providers_pkey;
       public            postgres    false    202            �
           2606    17435    bookings bookings_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.bookings DROP CONSTRAINT bookings_pkey;
       public            postgres    false    203            �
           2606    17437    guides guides_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.guides
    ADD CONSTRAINT guides_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.guides DROP CONSTRAINT guides_pkey;
       public            postgres    false    205            �
           2606    17439    locations locations_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.locations DROP CONSTRAINT locations_pkey;
       public            postgres    false    207            �
           2606    17441     review_guides review_guides_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.review_guides
    ADD CONSTRAINT review_guides_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.review_guides DROP CONSTRAINT review_guides_pkey;
       public            postgres    false    209            �
           2606    17443    review_tours review_tours_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.review_tours
    ADD CONSTRAINT review_tours_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.review_tours DROP CONSTRAINT review_tours_pkey;
       public            postgres    false    211            �
           2606    17445    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    213            �
           2606    17447    tour_images tour_images_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.tour_images
    ADD CONSTRAINT tour_images_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.tour_images DROP CONSTRAINT tour_images_pkey;
       public            postgres    false    215            �
           2606    17449 "   tour_locations tour_locations_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.tour_locations
    ADD CONSTRAINT tour_locations_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.tour_locations DROP CONSTRAINT tour_locations_pkey;
       public            postgres    false    217            �
           2606    17451    tour_types tour_types_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.tour_types
    ADD CONSTRAINT tour_types_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.tour_types DROP CONSTRAINT tour_types_pkey;
       public            postgres    false    219            �
           2606    17453    tours tours_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.tours
    ADD CONSTRAINT tours_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.tours DROP CONSTRAINT tours_pkey;
       public            postgres    false    221            �
           2606    17455 !   users uk6dotkott2kjsp8vw4d0m25fb7 
   CONSTRAINT     ]   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7;
       public            postgres    false    223            �
           2606    17457    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    223            �
           2606    17458 (   review_tours fk39x9tgriieqm3jgkoex33wpos    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_tours
    ADD CONSTRAINT fk39x9tgriieqm3jgkoex33wpos FOREIGN KEY (tour_id) REFERENCES public.tours(id);
 R   ALTER TABLE ONLY public.review_tours DROP CONSTRAINT fk39x9tgriieqm3jgkoex33wpos;
       public          postgres    false    211    221    2793            �
           2606    17463 !   tours fk3ar2uw482wtyadxn46ypqxjyk    FK CONSTRAINT     �   ALTER TABLE ONLY public.tours
    ADD CONSTRAINT fk3ar2uw482wtyadxn46ypqxjyk FOREIGN KEY (guide_id) REFERENCES public.guides(id);
 K   ALTER TABLE ONLY public.tours DROP CONSTRAINT fk3ar2uw482wtyadxn46ypqxjyk;
       public          postgres    false    221    2777    205            �
           2606    17468 )   review_guides fk4456ig5g72fd8la91i6gg4282    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_guides
    ADD CONSTRAINT fk4456ig5g72fd8la91i6gg4282 FOREIGN KEY (guide_id) REFERENCES public.guides(id);
 S   ALTER TABLE ONLY public.review_guides DROP CONSTRAINT fk4456ig5g72fd8la91i6gg4282;
       public          postgres    false    209    2777    205            �
           2606    17473 (   review_tours fk57rgbn2swlpquggy9wwgsc6ew    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_tours
    ADD CONSTRAINT fk57rgbn2swlpquggy9wwgsc6ew FOREIGN KEY (user_id) REFERENCES public.users(id);
 R   ALTER TABLE ONLY public.review_tours DROP CONSTRAINT fk57rgbn2swlpquggy9wwgsc6ew;
       public          postgres    false    223    211    2797            �
           2606    17478 *   tour_locations fk5rii550hmj37axh535de91g4u    FK CONSTRAINT     �   ALTER TABLE ONLY public.tour_locations
    ADD CONSTRAINT fk5rii550hmj37axh535de91g4u FOREIGN KEY (tour_id) REFERENCES public.tours(id);
 T   ALTER TABLE ONLY public.tour_locations DROP CONSTRAINT fk5rii550hmj37axh535de91g4u;
       public          postgres    false    2793    217    221            �
           2606    17483 !   tours fk954q05c5yddloovsg149elqug    FK CONSTRAINT     �   ALTER TABLE ONLY public.tours
    ADD CONSTRAINT fk954q05c5yddloovsg149elqug FOREIGN KEY (tour_type_id) REFERENCES public.tour_types(id);
 K   ALTER TABLE ONLY public.tours DROP CONSTRAINT fk954q05c5yddloovsg149elqug;
       public          postgres    false    221    2791    219            �
           2606    17488 "   guides fk9epk7rkhp13yrdhou2838dgam    FK CONSTRAINT     �   ALTER TABLE ONLY public.guides
    ADD CONSTRAINT fk9epk7rkhp13yrdhou2838dgam FOREIGN KEY (user_id) REFERENCES public.users(id);
 L   ALTER TABLE ONLY public.guides DROP CONSTRAINT fk9epk7rkhp13yrdhou2838dgam;
       public          postgres    false    2797    223    205            �
           2606    17493 $   bookings fkeyog2oic85xg7hsu2je2lx3s6    FK CONSTRAINT     �   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT fkeyog2oic85xg7hsu2je2lx3s6 FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.bookings DROP CONSTRAINT fkeyog2oic85xg7hsu2je2lx3s6;
       public          postgres    false    223    2797    203            �
           2606    17498 *   tour_locations fkg5q7j10p4myy25muaasfrbvl5    FK CONSTRAINT     �   ALTER TABLE ONLY public.tour_locations
    ADD CONSTRAINT fkg5q7j10p4myy25muaasfrbvl5 FOREIGN KEY (location_id) REFERENCES public.locations(id);
 T   ALTER TABLE ONLY public.tour_locations DROP CONSTRAINT fkg5q7j10p4myy25muaasfrbvl5;
       public          postgres    false    207    217    2779            �
           2606    17503 $   bookings fki21lisuytk5t7tlp7lv51ny2l    FK CONSTRAINT     �   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT fki21lisuytk5t7tlp7lv51ny2l FOREIGN KEY (tour_id) REFERENCES public.tours(id);
 N   ALTER TABLE ONLY public.bookings DROP CONSTRAINT fki21lisuytk5t7tlp7lv51ny2l;
       public          postgres    false    2793    203    221            �
           2606    17508 )   review_guides fkmg7l95jlhpxcuxu117ibn9pdq    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_guides
    ADD CONSTRAINT fkmg7l95jlhpxcuxu117ibn9pdq FOREIGN KEY (user_id) REFERENCES public.users(id);
 S   ALTER TABLE ONLY public.review_guides DROP CONSTRAINT fkmg7l95jlhpxcuxu117ibn9pdq;
       public          postgres    false    2797    223    209            �
           2606    17513 4   authentication_providers fkp33do4hsg1f15cdw1popaxbhn    FK CONSTRAINT     �   ALTER TABLE ONLY public.authentication_providers
    ADD CONSTRAINT fkp33do4hsg1f15cdw1popaxbhn FOREIGN KEY (user_id) REFERENCES public.users(id);
 ^   ALTER TABLE ONLY public.authentication_providers DROP CONSTRAINT fkp33do4hsg1f15cdw1popaxbhn;
       public          postgres    false    2797    202    223            �
           2606    17518 !   users fkp56c1712k691lhsyewcssf40f    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkp56c1712k691lhsyewcssf40f FOREIGN KEY (role_id) REFERENCES public.roles(id);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT fkp56c1712k691lhsyewcssf40f;
       public          postgres    false    2785    223    213            �
           2606    17523 '   tour_images fkth1m2rd6q6ltp8kii2msvfi5d    FK CONSTRAINT     �   ALTER TABLE ONLY public.tour_images
    ADD CONSTRAINT fkth1m2rd6q6ltp8kii2msvfi5d FOREIGN KEY (tour_id) REFERENCES public.tours(id);
 Q   ALTER TABLE ONLY public.tour_images DROP CONSTRAINT fkth1m2rd6q6ltp8kii2msvfi5d;
       public          postgres    false    2793    215    221            z   -   x��1�I��0�,K	K2s.�uO�.p�4N��)7�44����� �3
�      {   �  x���K�� D��s����|�,����5�Cc���(Y)OUvA���`�	� ������m�h%���}��^�ѡc_�Nŧɡ���4�vR:4���;ʌS>�}c{�L�Kc�֫�_�a�鍃v~�����8�n��Q"��'�3�._x��+5P��f�b�'ȁ��S�u��g�ٺ�x
�g�@}ߑ��^+9�^N�<���x�_Ȏ��o��I�{d�/�˓�k�SV�7�O�����~z�_��S6>�[�_���_��Ig�Ya>��#���vڞ�i���%`tq���I=��8�o��#7�N�z�0|��_�U�e���]�<8��n���li+���ً��.��+��Ԫզ����%�t��;-lw,�����u����l��<B��th      }   ~  x�UU;�E�gE9YFw�"�ɒW��yi�ޙ����t��e�$�� $��1��1��O૞]߱�j�]Uߣj��+�2GN����۽7�_R
ꚭ�=��5MZR���9.iT1�TbR�Y��ֵ4�-/IX́F��5�%9�q��Ҵ���������B�b���~�-����VuZ��GG����jE�>r0�n�5]ǁݽJ�lS��ʛ�J5#	�h�#�e:��U��D��v5IT����Pf�.��ƌ�a�EX��fg���gY��,�t5�5}�}�H&�K�b[�݃D�Q<��)ia:yb��H]6�%Żuu^��gG���Z=P� v&�Z`_����G4+;��q��Ӟ?�< �����߹�S��$�8�N��sp
b,i�V8Y�j�-1��G.,v�+D�"K~�B?���V4�Ԗ6��J-���;�z?K�TH؋m��n*�,.����r���O�i, �6m�Ia��v�(����ɱ��{P2*g ${�寉z_�IP�D�f�e�]��f��d�M����{Kα�Zk��I�ThP��(�c���uG$�K(R�.fXО��K�N"��p�������v/�G�o�n�=	b�Sa�8�Ko7�ݥύ�g9tRCu+�U��jvD���U��X�	�9&Do�TB?'���~@�����i���#���H�Ə�gXx�$��v�1�lӎ���@V9�#���w����7�|g������ף�So��)a�~�����!�dY;?�j��f;bL��d�:O�����y|�/N��Wã���$�5֊V"B�7׼�Z�����%�!���ūۛ�_��SN�*EI�v�~��3-���˿�d��xp:
�Yf��9��� `�=]F,x�۝�Q<�'�Y٭Gk`���Og3�������
�Q5 zf��Ձ��H���w�h��Ԗ˘�7�!%pܟĂ�T0�Rt���ۛ��������_��k��#L���_� ;�߉�w��؏٘r+3ٲ��2\��5L#�;-� �@�'�.��+�tq%>���Tܕ�T��(�(����X�jz*}	�����]��;T�5>�'�ó�ź^,�g���           x�e�ˎ�0E��W��%/�����`�n�ԨL�"c/���D?:�&o�KRT��&^�S���Li�S�������#hv:���B'bDa�ϔߺ��Nc�(Q���v�`��	�4e�&��q�|פ[�]ډ��	��_#*�r����&����k�$�i-�-�h���S�3xaC)�eF1O�y�v�H*S�*�O*vD�C�F�%�~�|���'~*=��r'��!(��?�v���)�`P��,yoȊ�
/�Y0`6�x�ه/5��)�d�Nݦ?�.��V���zʭTGJʳ�cOF
�ܾ�%��I����=?��$���R��U�?w}�
��^�a��>���@3�y 6�����{��Vl�j~�h��8P5�V��;̛P��*n�8�	��lxF�
OJ�>A��ߚrFȪ\~fxO�~�1��K��/=�����(aiy�st(�z����ՊA��"�:j�!�2�j㠳��&�k�	�<�m�|:�/ #�5�	¹�Ň7c�! �/V)      �     x���1nAE��)� ��3���)8%�)�"@���HК�F��������I��mn[ɛ�?�I%n�r�|��g ��5_�ƝOs'�
 5~��r��KR�����C�|��:��g�������ʨ�R���X,�o���UruT[	�i��4�k��Wmhא<��~>�m�Y��1)jCl�y�<؆/�P5�a0��`�?�g�PC�,6t�1�4Ђ��4Ђ5|ZL-�r�\-�6�`}�<��F��= �A      �   #  x��T�n�0�����8�I���%k�4(��Eᡟ���d������%mIE�$�Ieyŗjz[׏a�Y�SIE�`K��v��l����۲-_��\	��quΑ���:'��99�|�A�39@9Y胞�g?^��;��m*��CBJ�YZcL	��>���^m��@�0Bˠ�0�c!D��}c!D*��B��p���ɊG�ugc����2_��(�e��aZ��䥨Le!�v��T�C���2��!H�j+��z	3$�5tO� +K-�%t�k�vt��K����&�      �   $   x�3�tL����2�t/�LI�2�-N-����� k��      �   +  x�]��n�6�k�0+<3���Q7�&�vsT�Ro�,=�j�4T��w�����|?�`�1�y���k�.߆��q�|����x�^�B�P����n�n�o��!����.IR�R�Ծ ߜ�}~{/׷�a�{G�aOdl @� @
]��2]�y9,95�ᙒ(�F(�)���u�m������y�wx}��� 4�FLhJd�J�����)ͧ��2�ͯ�]��oi� r����i�A�~�Qjl������2ԏ7:��dH.q+AK�J�2l%i���t����a���X"�1U�}�(Hm��ȷ����r���I�@ił*�B*�TH�kn�C^n��o��Q�Q�K�^UB+B&�>x��V���Dux&T#RB��DJݺ@��X5@���:>��	��I!��9�Z�����\͵c*�}AT�mA2��x���SpD�*�)�QP"��yx?6�\�;�~��T`y/�*�!��k^N�s5tt	����P��.C�`Ff����)���\��ڃ))�Q��J}�DF]���� ���z�$0�EPŷ����8]�z;�� ,�.�bQ(JE�h(JE�oշ������h�USQ(�ES���<�e���u��[�-�#�Ͻ��q�@	�`	�!���]��j�|��(�]WKjP�WC3hը�o�ߟ�K`�]��J(�H!2��[�y�^u�� �=VC5(FjX,�Q1��ɟ���أ�6Z1	j��z��S���������p���9�c�m�?DP"�a��JĿ���Ǉ�9���Y�VKjP�WC3>��H��Oι�����      �   �  x�u��m1D�=U���ƯԒ���lq%��<�a�Q�r��h}���U���0�'Rˉ8�c�j��h]�9Ze��A���A����@r�}q��@��<ٴ@3���$��î�O��-p����ɩ�\vi�[
_�`IM0A�V
P=e���!����	D��v�ro#�n��U��v�I��*���U����r�Z��>���Y阺���^���y�!��iɓ���Oă7b�5w�sQKd��[>d��rH�C�!�$9�f� ���gX>�w��{��H���y,R��ëݧ��7KV����=�r����o��5��N*�ږב4�yB�����Y-�3Xh�A�����@� J���ǫ!c���}NPu~�������      �   /   x�3�J�I���K�2��L�()NM�9S�R�JJ�R�b���� !��      �      x��Z�r�Ɩ}����<'U�ɒu�#�%���ē�yiM�# ������Z�*�9OS��$ї����ڻ��4�������L��JlsP��[[�<?(o�VJ{<�\nS}P��7֕Se��Ծ��&U{�d�}m�$S;��V�jݖ[����a��|9?�_�.��8���^7�ٵ�d1��'�s�]���t���c�E�e�j2�~�j��u�0��ZȒ窩�����Ԩ0_�(�r�F��������$<Z=Ɍ�p^�o$x�۬y2Ɩ���gZ���"��6����5�T��(���Ƌ�R���g�bs�F����b�� #���q�k�Fc�Ѫ���ebM٨�{g�5�=�#4�_y�Gʇ2�I'@��Rt.Ύ�vr�*W����ޘ��@/jS�B��<�L���.y��n�W�̔jmt���F�;xJ���o�n<�*���l�5J�Ê_L���^�����Ħ"��-_�ޝm��Sx ��j�m��q��Նn�Z�A��O�OA�(;���-�U�~	2^�i��?*�u���������R�j]T�8���<R�Y?T}�D�����!�m�Bqp0]6�ŷ%�A���278��O\ �bs�/�B���T��~d�X��߉_
CY�;�v(p���z>�|؄x²T�����V�pE,�W�d���Y��֍�:N��������V@��
:n�(Z�����LW��K�i~�MVGʞO�������䵗1"�i��M��']i���⾀�q������&�3���)u��>�[����6��4�+h5���g∭1�����8P��Ƥ��(���J@�p�����y@����9赋� ���uy
_ k\� ��p� !�(pP_��Yˑ_,�=	�T�9�&S�6�=�*�m5 ff��֕7O<9|��?��z�޶�A��K"8}��z����jK�g�	}�5��`c$�x�p���kZ�sm��-�$7��e�@_����pG��5�MS���E��ӛ���l1�d�>׶"X��Fyh96�|%�^�W��3�!q=��=�V=����5�WUf0j�J�yL耑6 g6�����'�����b���k���<�� &v��?	~>hq�G����s/~Z�yk����g��i|�yP?\S �3,
�)��Mfa�����m>ሏH�w�"[�r���.b��ڀM�FCژ�?��F	�O�ń"��;��G�$���Lm��뮃�7������a�Y#�ηd���&*�On4$�(u�X���5����@"G	I��	v����.͘Ѕn9C̷�T#D�#�f����ngv�9�!��L��LL2��H|\��0�a���E5��Nۼ�N��3us�}w����
2�����#l��r>���ǒ��q�kFQ��8���<��M\��;=������R�HxϮR�s�FmE�$�)y(� ٭���L�VL0�R��R���k�ٔ�z��VDV�	Y1��;'P�݉H}���M�[4+",yYqkϞ{Ht$ K.�y�eG)�i�9ϙk[��.��9�� udnAP"�{ )Μ(�Iz�@ri���0�q�ۉ����{�N">�t6��aE6� (�z/Ծ4�`xA̖�	���r6!��1�YfӘ��!/�g8��sN�\�y>�����&p{d'An�r�X%�_Mt�dB9���|� �%�A�<U�3��"���g�N7#0
�w��3�� q�����j���ŕ$!�Ԇ����
6��D�@�:�":�!�ں���5B������@X��7�F�F�DXN�T�(���.�pJ}�FJ��QP�yl�%̲�|�L\p�9A2!�:�����cp��9HN���q���2���K�%�����YuU�],G�y ~]�ǁB2	�W�T��:F�^U���|X�D��E��{�������ͧ���wL ���W�M��׆���0�#$c0|���E�Au܍ˀ�R<��kȣ�k�n�4�
;v|!3�ɻ��Ngb8�a��66�%�-��pv)�� �uks�'ڥ&��泵B�����=���D�;�5�B"h�V=�:�t�GFA6�
i��,>�36e�q��8��d�De�H_YhED�	�,��r:���q�c�@=AagC3y��z$(�p�Gg�>��N��~EF�`5-�Zp�5���`�H^��uM����V,?%�v}A��#�Q��-�fnP�1z�-\P*�c�w	Y��T6�8lsCJ��C�;�qXL"I!8G�A�t�ʋ���c�๮��`���m�����g/.�"k�dV�AWa����典cXY���_��P�KF��uZ�Aiw��l�=?�5#�秳9�!�9��q��C�	�{-����'ʆ��ރh��\V���ոr@*{�j�ϩ�;-Ӥ���H���k0a����<B�Rm!:�`0�����EpMM����l)H�:��1U��X�i.O���c��2�L��KL�c�� qؚ�"G�O�2P��hE}0/<֭�������u8X���p��U�.^טD��~$�o�O���2N��{�GN*�YH\����~����i��v�P���e*�A� ���߻��V�1��5�x��J���n�._X3�$%45�pP̽T���aJ�A)�3�8A�����D�M�Υ�A	;���n�CltT�N�?��a��h���7F�S�� 7�����Z�EwxuP�Ը��զ�L��S,Nю&���2���1�26�+��_�YղD�mCo7�W׆L���r7iK3SA�Bg0�xyܗ��2�,@ZuG�ɰ�eN&,��
9�`.ñ�n��ʙE�9a���(���?Ėc���ntp*�M�c����:oe�i�šC�tw��|����
;M;b�7=C��a%���n�nFyoG5�[b�0M�Q"@�������\�m�g�_N������Z&��>e���"0~�A4���0Ҕ]��׀��0\[4�+Q��9�s9y�(�3�ژ�Ksu4;���F2�0�0o|�t���+�I琰��m�f~�Ht�� �cr��zYD���*��ޓ��<����j��'9Lǳ`�/� xv�ڏ�{��^}(H�1yM�9��D�ꡍ���P�sSxEZ㷒��
�ﳩ7��P�* 8�j>�
w]��5AΎ��G� kk�R@�|��N�3�@�U�D�ocp���]Q"J�m�"�MteYV�����?n��p{(Q.�u��8��N���G����WMg]�1�E �A&�tb��	k6�F}���B�M5�ݙ�-Q�!�e�=�����yQH��l��1)�1�G}�X��$�����I�6�2��8@��=[�щXqCa���	�y�v��� >)#E<Ɠ�2�M@���$��iK΍��ʹk�"��&�+^)f�3As�9�P�p��0~��%��܀Ţ�q�Q,y�"��u͘�_��%����w��u������6�gk���4{^&u`:;E��k�����B[o$��$�]��wպM����RJ!�H�x�Z��i牥٫�:��Q!�rM��D����/E����Ow�P2�*~&����M�H:�eL͘��\�o��e�k�A������K]̾Z" �4�6Z辤G]�K�_��ΐ%�K�f�3!��j�+L�9����\�pq`O��y4@�_c����Y�� 8p.�~��(�R�\_�}j�ť�Z�!` H��ҿ����_N������W}GC�J�H�M�O��MJ�_zlDWrAk�A!�q��&�I��3���Vg2kL�]�f�&/�6݌yR�ߧ�q	�5އ^�{w��Q�	G�O��WIlhw[�m�Fq�3f�Q�E9R����ľ�		�2̞��UTBm�i�脛n��]���0��L�b@��֟��7RX�� �l�����>�=��p����@��}$��P'���{������E��t����W�s�x�*k��.������}�� D  ?̹��Ǿ�\�vo\�L��|�.����6�aP�ʑ9z���,���~�҅��hrt>�oܼ�{2$��E��z�ddŚ��ڇ�}�,�ݶ��6ja��7��(7�5 챭CW�`ޗ���Z���O(k�\�Q�唭d R����ȊO��F*A?\��Ԗ]�o�RP�׎ᵅq��?��n�D*@`0�5�xE0�_�����/��cH��7l!1��h1��]�� �GA�)���h������������0��!�5CR�,�>rM�B��Y�,I��!��>��P3�r,L9X�c \������I�/b��	EH?��M�o��8�4��w�N[�p�Z.xBc��k����n�8�6L�A�{��:��ņС�i�`��ݭ���`R���z���]`V.�[T���R�-����$�c!���-���|wU��e�t��l���5
�55���C��}�w���� �(}+�i�e}���c3$M��:�`������w|X���[��u�P= �%7wWgs�R�!��Ρ�jy�Dvp\��݇��׽@�0ІpG2���~h���������:�      �   �  x�e�ݎ�0���S���(`���j�v+��ݢ�ZE�(a�[bP����w��.AH�<�3�81D3���o���;6��צ�Ư�c�N�������|(������<ȋk����?��j{�q{+²>l�{U���a�-doz���#+���Ȳ0	���q0~s���&���YZ��&LSKz��xK�|��B�۞�����o�c�:��gz�!�"����0�H�hs���ZP9����_qx5e���������@+�H�}��W�*Z��ti�f"�@f@v�3��װ�J�	w���Q5vBQ]�*�BP�R  �R-'rRF:l	!4X��$p�%��½��f�����x��b.
�/��k0kq㘋3s�a�\6}��Z֜�W8Z�V�W����L��W�Epv��p�J��;D7\�If���<��+     