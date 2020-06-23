PGDMP     	    
                 x            sem4    12.3    12.3 c    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    348616    sem4    DATABASE     �   CREATE DATABASE sem4 WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE sem4;
                postgres    false            �            1259    348617    authentication_providers    TABLE     x   CREATE TABLE public.authentication_providers (
    provider_key character varying(255) NOT NULL,
    user_id integer
);
 ,   DROP TABLE public.authentication_providers;
       public         heap    postgres    false            �            1259    348620    bookings    TABLE     �  CREATE TABLE public.bookings (
    id integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    is_cancelled boolean DEFAULT true NOT NULL,
    paid boolean,
    price numeric(19,2) NOT NULL,
    quantity integer NOT NULL,
    tour_id integer NOT NULL,
    user_id integer NOT NULL,
    deadline_cancelled timestamp without time zone,
    start_date date,
    end_date date
);
    DROP TABLE public.bookings;
       public         heap    postgres    false            �            1259    348624    bookings_id_seq    SEQUENCE     �   CREATE SEQUENCE public.bookings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.bookings_id_seq;
       public          postgres    false    203            �           0    0    bookings_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.bookings_id_seq OWNED BY public.bookings.id;
          public          postgres    false    204            �            1259    348626    guides    TABLE     �   CREATE TABLE public.guides (
    id integer NOT NULL,
    description character varying(255) NOT NULL,
    number_of_ratings integer NOT NULL,
    rating_average numeric(19,2) NOT NULL,
    user_id integer NOT NULL
);
    DROP TABLE public.guides;
       public         heap    postgres    false            �            1259    348629    guides_id_seq    SEQUENCE     �   CREATE SEQUENCE public.guides_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.guides_id_seq;
       public          postgres    false    205            �           0    0    guides_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.guides_id_seq OWNED BY public.guides.id;
          public          postgres    false    206            �            1259    348631 	   locations    TABLE       CREATE TABLE public.locations (
    id integer NOT NULL,
    address character varying(255) NOT NULL,
    is_active boolean DEFAULT true,
    latitude numeric(19,2) NOT NULL,
    longitude numeric(19,2) NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.locations;
       public         heap    postgres    false            �            1259    348638    locations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.locations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.locations_id_seq;
       public          postgres    false    207            �           0    0    locations_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.locations_id_seq OWNED BY public.locations.id;
          public          postgres    false    208            �            1259    348640    review_guides    TABLE     $  CREATE TABLE public.review_guides (
    id integer NOT NULL,
    active boolean DEFAULT true NOT NULL,
    created_at timestamp without time zone NOT NULL,
    rating integer NOT NULL,
    review character varying(255) NOT NULL,
    guide_id integer NOT NULL,
    user_id integer NOT NULL
);
 !   DROP TABLE public.review_guides;
       public         heap    postgres    false            �            1259    348644    review_guides_id_seq    SEQUENCE     �   CREATE SEQUENCE public.review_guides_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.review_guides_id_seq;
       public          postgres    false    209            �           0    0    review_guides_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.review_guides_id_seq OWNED BY public.review_guides.id;
          public          postgres    false    210            �            1259    348646    review_tours    TABLE     "  CREATE TABLE public.review_tours (
    id integer NOT NULL,
    active boolean DEFAULT true NOT NULL,
    created_at timestamp without time zone NOT NULL,
    rating integer NOT NULL,
    review character varying(255) NOT NULL,
    tour_id integer NOT NULL,
    user_id integer NOT NULL
);
     DROP TABLE public.review_tours;
       public         heap    postgres    false            �            1259    348650    review_tours_id_seq    SEQUENCE     �   CREATE SEQUENCE public.review_tours_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.review_tours_id_seq;
       public          postgres    false    211            �           0    0    review_tours_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.review_tours_id_seq OWNED BY public.review_tours.id;
          public          postgres    false    212            �            1259    348652    roles    TABLE     a   CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    348655    roles_id_seq    SEQUENCE     �   CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    213            �           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    214            �            1259    348657    tour_images    TABLE     �   CREATE TABLE public.tour_images (
    id integer NOT NULL,
    image_url character varying(255) NOT NULL,
    tour_id integer
);
    DROP TABLE public.tour_images;
       public         heap    postgres    false            �            1259    348660    tour_images_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tour_images_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.tour_images_id_seq;
       public          postgres    false    215            �           0    0    tour_images_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.tour_images_id_seq OWNED BY public.tour_images.id;
          public          postgres    false    216            �            1259    348662    tour_locations    TABLE     �   CREATE TABLE public.tour_locations (
    id integer NOT NULL,
    date timestamp without time zone NOT NULL,
    location_id integer NOT NULL,
    tour_id integer NOT NULL
);
 "   DROP TABLE public.tour_locations;
       public         heap    postgres    false            �            1259    348665    tour_locations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tour_locations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.tour_locations_id_seq;
       public          postgres    false    217            �           0    0    tour_locations_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.tour_locations_id_seq OWNED BY public.tour_locations.id;
          public          postgres    false    218            �            1259    348667 
   tour_types    TABLE     f   CREATE TABLE public.tour_types (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.tour_types;
       public         heap    postgres    false            �            1259    348670    tour_types_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tour_types_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.tour_types_id_seq;
       public          postgres    false    219            �           0    0    tour_types_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.tour_types_id_seq OWNED BY public.tour_types.id;
          public          postgres    false    220            �            1259    348672    tours    TABLE     f  CREATE TABLE public.tours (
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
       public         heap    postgres    false            �            1259    348679    tours_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tours_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.tours_id_seq;
       public          postgres    false    221            �           0    0    tours_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.tours_id_seq OWNED BY public.tours.id;
          public          postgres    false    222            �            1259    348681    users    TABLE     ;  CREATE TABLE public.users (
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
       public         heap    postgres    false            �            1259    348688    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    223            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    224            �
           2604    348690    bookings id    DEFAULT     j   ALTER TABLE ONLY public.bookings ALTER COLUMN id SET DEFAULT nextval('public.bookings_id_seq'::regclass);
 :   ALTER TABLE public.bookings ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    204    203            �
           2604    348691 	   guides id    DEFAULT     f   ALTER TABLE ONLY public.guides ALTER COLUMN id SET DEFAULT nextval('public.guides_id_seq'::regclass);
 8   ALTER TABLE public.guides ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    205            �
           2604    348692    locations id    DEFAULT     l   ALTER TABLE ONLY public.locations ALTER COLUMN id SET DEFAULT nextval('public.locations_id_seq'::regclass);
 ;   ALTER TABLE public.locations ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    208    207            �
           2604    348693    review_guides id    DEFAULT     t   ALTER TABLE ONLY public.review_guides ALTER COLUMN id SET DEFAULT nextval('public.review_guides_id_seq'::regclass);
 ?   ALTER TABLE public.review_guides ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209            �
           2604    348694    review_tours id    DEFAULT     r   ALTER TABLE ONLY public.review_tours ALTER COLUMN id SET DEFAULT nextval('public.review_tours_id_seq'::regclass);
 >   ALTER TABLE public.review_tours ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211            �
           2604    348695    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    213            �
           2604    348696    tour_images id    DEFAULT     p   ALTER TABLE ONLY public.tour_images ALTER COLUMN id SET DEFAULT nextval('public.tour_images_id_seq'::regclass);
 =   ALTER TABLE public.tour_images ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215            �
           2604    348697    tour_locations id    DEFAULT     v   ALTER TABLE ONLY public.tour_locations ALTER COLUMN id SET DEFAULT nextval('public.tour_locations_id_seq'::regclass);
 @   ALTER TABLE public.tour_locations ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217            �
           2604    348698    tour_types id    DEFAULT     n   ALTER TABLE ONLY public.tour_types ALTER COLUMN id SET DEFAULT nextval('public.tour_types_id_seq'::regclass);
 <   ALTER TABLE public.tour_types ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219            �
           2604    348699    tours id    DEFAULT     d   ALTER TABLE ONLY public.tours ALTER COLUMN id SET DEFAULT nextval('public.tours_id_seq'::regclass);
 7   ALTER TABLE public.tours ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221            �
           2604    348700    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223            y          0    348617    authentication_providers 
   TABLE DATA           I   COPY public.authentication_providers (provider_key, user_id) FROM stdin;
    public          postgres    false    202   �v       z          0    348620    bookings 
   TABLE DATA           �   COPY public.bookings (id, created_at, is_cancelled, paid, price, quantity, tour_id, user_id, deadline_cancelled, start_date, end_date) FROM stdin;
    public          postgres    false    203   *w       |          0    348626    guides 
   TABLE DATA           ]   COPY public.guides (id, description, number_of_ratings, rating_average, user_id) FROM stdin;
    public          postgres    false    205   �w       ~          0    348631 	   locations 
   TABLE DATA           V   COPY public.locations (id, address, is_active, latitude, longitude, name) FROM stdin;
    public          postgres    false    207   �x       �          0    348640    review_guides 
   TABLE DATA           b   COPY public.review_guides (id, active, created_at, rating, review, guide_id, user_id) FROM stdin;
    public          postgres    false    209   �z       �          0    348646    review_tours 
   TABLE DATA           `   COPY public.review_tours (id, active, created_at, rating, review, tour_id, user_id) FROM stdin;
    public          postgres    false    211   |       �          0    348652    roles 
   TABLE DATA           )   COPY public.roles (id, name) FROM stdin;
    public          postgres    false    213   }       �          0    348657    tour_images 
   TABLE DATA           =   COPY public.tour_images (id, image_url, tour_id) FROM stdin;
    public          postgres    false    215   :}       �          0    348662    tour_locations 
   TABLE DATA           H   COPY public.tour_locations (id, date, location_id, tour_id) FROM stdin;
    public          postgres    false    217   u�       �          0    348667 
   tour_types 
   TABLE DATA           .   COPY public.tour_types (id, name) FROM stdin;
    public          postgres    false    219   �       �          0    348672    tours 
   TABLE DATA           �   COPY public.tours (id, active, current_group_size, description, duration, end_date, max_group_size, name, number_of_ratings, price, price_discount, rating_average, start_date, summary, tour_image_cover, guide_id, tour_type_id) FROM stdin;
    public          postgres    false    221   P�       �          0    348681    users 
   TABLE DATA           `   COPY public.users (id, active, avatar_image, email, name, password, phone, role_id) FROM stdin;
    public          postgres    false    223   Y�       �           0    0    bookings_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.bookings_id_seq', 15, true);
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
           2606    348702 6   authentication_providers authentication_providers_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public.authentication_providers
    ADD CONSTRAINT authentication_providers_pkey PRIMARY KEY (provider_key);
 `   ALTER TABLE ONLY public.authentication_providers DROP CONSTRAINT authentication_providers_pkey;
       public            postgres    false    202            �
           2606    348704    bookings bookings_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.bookings DROP CONSTRAINT bookings_pkey;
       public            postgres    false    203            �
           2606    348706    guides guides_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.guides
    ADD CONSTRAINT guides_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.guides DROP CONSTRAINT guides_pkey;
       public            postgres    false    205            �
           2606    348708    locations locations_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.locations DROP CONSTRAINT locations_pkey;
       public            postgres    false    207            �
           2606    348710     review_guides review_guides_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.review_guides
    ADD CONSTRAINT review_guides_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.review_guides DROP CONSTRAINT review_guides_pkey;
       public            postgres    false    209            �
           2606    348712    review_tours review_tours_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.review_tours
    ADD CONSTRAINT review_tours_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.review_tours DROP CONSTRAINT review_tours_pkey;
       public            postgres    false    211            �
           2606    348714    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    213            �
           2606    348716    tour_images tour_images_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.tour_images
    ADD CONSTRAINT tour_images_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.tour_images DROP CONSTRAINT tour_images_pkey;
       public            postgres    false    215            �
           2606    348718 "   tour_locations tour_locations_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.tour_locations
    ADD CONSTRAINT tour_locations_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.tour_locations DROP CONSTRAINT tour_locations_pkey;
       public            postgres    false    217            �
           2606    348720    tour_types tour_types_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.tour_types
    ADD CONSTRAINT tour_types_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.tour_types DROP CONSTRAINT tour_types_pkey;
       public            postgres    false    219            �
           2606    348722    tours tours_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.tours
    ADD CONSTRAINT tours_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.tours DROP CONSTRAINT tours_pkey;
       public            postgres    false    221            �
           2606    348724 !   users uk6dotkott2kjsp8vw4d0m25fb7 
   CONSTRAINT     ]   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7;
       public            postgres    false    223            �
           2606    348726    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    223            �
           2606    348727 (   review_tours fk39x9tgriieqm3jgkoex33wpos    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_tours
    ADD CONSTRAINT fk39x9tgriieqm3jgkoex33wpos FOREIGN KEY (tour_id) REFERENCES public.tours(id);
 R   ALTER TABLE ONLY public.review_tours DROP CONSTRAINT fk39x9tgriieqm3jgkoex33wpos;
       public          postgres    false    221    2792    211            �
           2606    348732 !   tours fk3ar2uw482wtyadxn46ypqxjyk    FK CONSTRAINT     �   ALTER TABLE ONLY public.tours
    ADD CONSTRAINT fk3ar2uw482wtyadxn46ypqxjyk FOREIGN KEY (guide_id) REFERENCES public.guides(id);
 K   ALTER TABLE ONLY public.tours DROP CONSTRAINT fk3ar2uw482wtyadxn46ypqxjyk;
       public          postgres    false    205    2776    221            �
           2606    348737 )   review_guides fk4456ig5g72fd8la91i6gg4282    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_guides
    ADD CONSTRAINT fk4456ig5g72fd8la91i6gg4282 FOREIGN KEY (guide_id) REFERENCES public.guides(id);
 S   ALTER TABLE ONLY public.review_guides DROP CONSTRAINT fk4456ig5g72fd8la91i6gg4282;
       public          postgres    false    209    2776    205            �
           2606    348742 (   review_tours fk57rgbn2swlpquggy9wwgsc6ew    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_tours
    ADD CONSTRAINT fk57rgbn2swlpquggy9wwgsc6ew FOREIGN KEY (user_id) REFERENCES public.users(id);
 R   ALTER TABLE ONLY public.review_tours DROP CONSTRAINT fk57rgbn2swlpquggy9wwgsc6ew;
       public          postgres    false    211    223    2796            �
           2606    348747 *   tour_locations fk5rii550hmj37axh535de91g4u    FK CONSTRAINT     �   ALTER TABLE ONLY public.tour_locations
    ADD CONSTRAINT fk5rii550hmj37axh535de91g4u FOREIGN KEY (tour_id) REFERENCES public.tours(id);
 T   ALTER TABLE ONLY public.tour_locations DROP CONSTRAINT fk5rii550hmj37axh535de91g4u;
       public          postgres    false    2792    217    221            �
           2606    348752 !   tours fk954q05c5yddloovsg149elqug    FK CONSTRAINT     �   ALTER TABLE ONLY public.tours
    ADD CONSTRAINT fk954q05c5yddloovsg149elqug FOREIGN KEY (tour_type_id) REFERENCES public.tour_types(id);
 K   ALTER TABLE ONLY public.tours DROP CONSTRAINT fk954q05c5yddloovsg149elqug;
       public          postgres    false    221    219    2790            �
           2606    348757 "   guides fk9epk7rkhp13yrdhou2838dgam    FK CONSTRAINT     �   ALTER TABLE ONLY public.guides
    ADD CONSTRAINT fk9epk7rkhp13yrdhou2838dgam FOREIGN KEY (user_id) REFERENCES public.users(id);
 L   ALTER TABLE ONLY public.guides DROP CONSTRAINT fk9epk7rkhp13yrdhou2838dgam;
       public          postgres    false    2796    205    223            �
           2606    348762 $   bookings fkeyog2oic85xg7hsu2je2lx3s6    FK CONSTRAINT     �   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT fkeyog2oic85xg7hsu2je2lx3s6 FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.bookings DROP CONSTRAINT fkeyog2oic85xg7hsu2je2lx3s6;
       public          postgres    false    2796    223    203            �
           2606    348767 *   tour_locations fkg5q7j10p4myy25muaasfrbvl5    FK CONSTRAINT     �   ALTER TABLE ONLY public.tour_locations
    ADD CONSTRAINT fkg5q7j10p4myy25muaasfrbvl5 FOREIGN KEY (location_id) REFERENCES public.locations(id);
 T   ALTER TABLE ONLY public.tour_locations DROP CONSTRAINT fkg5q7j10p4myy25muaasfrbvl5;
       public          postgres    false    207    217    2778            �
           2606    348772 $   bookings fki21lisuytk5t7tlp7lv51ny2l    FK CONSTRAINT     �   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT fki21lisuytk5t7tlp7lv51ny2l FOREIGN KEY (tour_id) REFERENCES public.tours(id);
 N   ALTER TABLE ONLY public.bookings DROP CONSTRAINT fki21lisuytk5t7tlp7lv51ny2l;
       public          postgres    false    2792    203    221            �
           2606    348777 )   review_guides fkmg7l95jlhpxcuxu117ibn9pdq    FK CONSTRAINT     �   ALTER TABLE ONLY public.review_guides
    ADD CONSTRAINT fkmg7l95jlhpxcuxu117ibn9pdq FOREIGN KEY (user_id) REFERENCES public.users(id);
 S   ALTER TABLE ONLY public.review_guides DROP CONSTRAINT fkmg7l95jlhpxcuxu117ibn9pdq;
       public          postgres    false    223    209    2796            �
           2606    348782 4   authentication_providers fkp33do4hsg1f15cdw1popaxbhn    FK CONSTRAINT     �   ALTER TABLE ONLY public.authentication_providers
    ADD CONSTRAINT fkp33do4hsg1f15cdw1popaxbhn FOREIGN KEY (user_id) REFERENCES public.users(id);
 ^   ALTER TABLE ONLY public.authentication_providers DROP CONSTRAINT fkp33do4hsg1f15cdw1popaxbhn;
       public          postgres    false    202    223    2796            �
           2606    348787 !   users fkp56c1712k691lhsyewcssf40f    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkp56c1712k691lhsyewcssf40f FOREIGN KEY (role_id) REFERENCES public.roles(id);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT fkp56c1712k691lhsyewcssf40f;
       public          postgres    false    2784    223    213            �
           2606    348792 '   tour_images fkth1m2rd6q6ltp8kii2msvfi5d    FK CONSTRAINT     �   ALTER TABLE ONLY public.tour_images
    ADD CONSTRAINT fkth1m2rd6q6ltp8kii2msvfi5d FOREIGN KEY (tour_id) REFERENCES public.tours(id);
 Q   ALTER TABLE ONLY public.tour_images DROP CONSTRAINT fkth1m2rd6q6ltp8kii2msvfi5d;
       public          postgres    false    221    2792    215            y   -   x��1�I��0�,K	K2s.�uO�.p�4N��)7�44����� �3
�      z   �   x����� �=E �3?���	��J+J �#[�PRQq����{i�Ad)%>Gh{և�	�ҳ�`a
P�6�
�)b+U8M��u
P�&���zN ���y���@���N�(�!��[Y~��*�H���&��
q�P�±�u���*��y��C<�����o[�	      |   �   x��ϱ�0���)�	R@g]tdri�++5���YLܿ���6p�ސ����@ulӵ�ǦfP�'i
fPqxё��F7;�l�zN.R��=�2��GiH��- ��g�6w÷��N����a�|�yKU�Bg}�l�E
%����-n�uvJ�z4j2;�$���Sp      ~     x�e�ˎ�0E��W��%/�����`�n�ԨL�"c/���D?:�&o�KRT��&^�S���Li�S�������#hv:���B'bDa�ϔߺ��Nc�(Q���v�`��	�4e�&��q�|פ[�]ډ��	��_#*�r����&����k�$�i-�-�h���S�3xaC)�eF1O�y�v�H*S�*�O*vD�C�F�%�~�|���'~*=��r'��!(��?�v���)�`P��,yoȊ�
/�Y0`6�x�ه/5��)�d�Nݦ?�.��V���zʭTGJʳ�cOF
�ܾ�%��I����=?��$���R��U�?w}�
��^�a��>���@3�y 6�����{��Vl�j~�h��8P5�V��;̛P��*n�8�	��lxF�
OJ�>A��ߚrFȪ\~fxO�~�1��K��/=�����(aiy�st(�z����ՊA��"�:j�!�2�j㠳��&�k�	�<�m�|:�/ #�5�	¹�Ň7c�! �/V)      �   &  x���;O�0�g�W܍�"?����Њ���57�ؒ�ʿ�v�f�R�l9>�;�H61%�X�j��?��63Cf&�\����nh0)%ׄ�d[7�i�����v᪒�$�*�Wt�e�J�ִ�R����T�Po��N�R���=�y𓳦��#��0��"^䑒�y��T$ I���l�����$�\���S$���+�	��	��T�ڳ`;k8{>b�ey�@��V��c����1�{h�aUƤv./��=��%�H-���a�7�:_�Z�`�g�v��-�ɿ8�?�=�)      �   �   x����j�0���S(�a�i��ޕ]v�u�ҊԔ��q���ge��"(����%`�~�{��y>��6��?���cC�����!�`:�'��},qTʮ5�ag�\��78�cg�\�Y�)/����Wǧ�:�5'y��FJ'2]_�4�/��Y�)ƱH�I9X�����e8jY�t��#G�>[�hR94MS�hVk�6W�?3���V�A���r[x���E�tlι_Թ�4      �   $   x�3�tL����2�t/�LI�2�-N-����� k��      �   +  x�]��n�6�k�0+<3���Q7�&�vsT�Ro�,=�j�4T��w�����|?�`�1�y���k�.߆��q�|����x�^�B�P����n�n�o��!����.IR�R�Ծ ߜ�}~{/׷�a�{G�aOdl @� @
]��2]�y9,95�ᙒ(�F(�)���u�m������y�wx}��� 4�FLhJd�J�����)ͧ��2�ͯ�]��oi� r����i�A�~�Qjl������2ԏ7:��dH.q+AK�J�2l%i���t����a���X"�1U�}�(Hm��ȷ����r���I�@ił*�B*�TH�kn�C^n��o��Q�Q�K�^UB+B&�>x��V���Dux&T#RB��DJݺ@��X5@���:>��	��I!��9�Z�����\͵c*�}AT�mA2��x���SpD�*�)�QP"��yx?6�\�;�~��T`y/�*�!��k^N�s5tt	����P��.C�`Ff����)���\��ڃ))�Q��J}�DF]���� ���z�$0�EPŷ����8]�z;�� ,�.�bQ(JE�h(JE�oշ������h�USQ(�ES���<�e���u��[�-�#�Ͻ��q�@	�`	�!���]��j�|��(�]WKjP�WC3hը�o�ߟ�K`�]��J(�H!2��[�y�^u�� �=VC5(FjX,�Q1��ɟ���أ�6Z1	j��z��S���������p���9�c�m�?DP"�a��JĿ���Ǉ�9���Y�VKjP�WC3>��H��Oι�����      �   �  x�u��m1D�=U���ƯԒ���lq%��<�a�Q�r��h}���U���0�'Rˉ8�c�j��h]�9Ze��A���A����@r�}q��@��<ٴ@3���$��î�O��-p����ɩ�\vi�[
_�`IM0A�V
P=e���!����	D��v�ro#�n��U��v�I��*���U����r�Z��>���Y阺���^���y�!��iɓ���Oă7b�5w�sQKd��[>d��rH�C�!�$9�f� ���gX>�w��{��H���y,R��ëݧ��7KV����=�r����o��5��N*�ږב4�yB�����Y-�3Xh�A�����@� J���ǫ!c���}NPu~�������      �   /   x�3�J�I���K�2��L�()NM�9S�R�JJ�R�b���� !��      �      x��Z�r�Ʋ}��bj?�I�"����K�X�},{���!9�A0�h���Z�����r�D`��/k����Q;��.��<����DSv�5u�dk�qU�lM�'�F��i��T������m�d~>����i���r~�8c�o�����奏o,f۶���W���n'���|:�M_O��荭Z왙k�P�̥ݎ��)?���N���}s~:z��)<����ڕo��g��
��؈���Z��/���?X�����E���~�>j|tQ�hrm]_��&pa�j�,���T7����&_uE�5n�g��f/fS��#6m��u6�E?/�P�;؁u�ó+���$������k��#|������8^�{zw������}������{s竵i]YN�y����~��DC�r�y
˵�`�����S�Ͼ�sF��g�/�0&,�}�b�5H7f�k��.7���mB�3�����N�K�J��7�]�9��gmrH]YXrE����/f3q�ő̍G��H��nK�7c�c/�3va��ɼ��x�}�\�e�
1[8����?��O������N+�/fk_䍓��������p~�6G�D�hx|m�]�΄���>����1l�F��xɽdpr���w���}�ʙ��/�"�rCY~
M��E��BY�*�8��X����kTiiW����P�ө��J��t�m�S�����-m�"d��[oX���v�A� 2��)YӴ�V0��M���Wn��OÙ>�L��K����[�����/��P��hU!�+��Y �/Z�i�H26M�}c����qq����+��u�SH�����q��qh�X+�����,b:���=�"��A�,��ѻ���؈�ⁿ�<)��`aYTKm�s��:���� XН^fdc�ޏt�#���������D"��纀�Ϳ]����/�0s<!5��u%!(�|��kW�|��Ϡ@��m�eb�P"����8Ekg��/a�g�v3B,}�|���U�e��U��d4���9�>��p�ƹ������Fh���2�Yj�{ ��(`#O N{�21�I �Y��o
���5�y��`�J����l�"X!�0�Z��5a�*�L��4���6f�����6��ľ���v8�*�<����� ��˲���_������`�ړ��G1�Ι��Cj�zb8�O��Z��t��Ei����3���8M�C�n��S�.*@��%
<�BolSr���[��g�� �@�����h��GYq���<J+D'�/��n �q1��7���#[�pB<��ʇ"��y����V�N
�Q��%,w�z��7�Η�F��Ġ�F8$�R���0�*�L�->oU��H-�AB��hf� ��^��v"�r�E<�d\��=ba��	�p�S�ȹ��.g6N+�U3Q,b�U��W�r4��RC�
ϸ�`��K�ǩru�@�������*��à�GSd�I/��B;�R�C�K�Q�d4C���F��^B�"$ȟ�RȜ�:Ȋ$��C���O�S��}�/P�%�@�_�O$�v����0$y�$�� -j�\ )�т�8Ca�a\�*{o���!�7o��-+��ugn����G���'����е���(q"/��ؽ7�IFl�������e$�f-�A9C�dB8k��t 6MA��ұĭ�=��s��z�7P3�vE�@rQt�Ә
�`�d	jf~���ҝ�}8߯;�J�p�B
�RB����U�{���� k��Ж��X�O�5�BWPQD��^ ����Ґ�n�Z�{�G��X�J��&��U���h��sK�v�m'_u J�YS|������﵅�IXm��l-���veEG�c)�)�����}�$wJ,PrHߥ~�@�c-J�F�� ��F�x���]���hYW.��ȵ-P_%���ԕ�ְܶ+�:���IO<<�� �P
��6��+g���㓊��m�� �E� �k�b,}��z���@���� Nw'wS	�e���H���Ts�
t�p�Uye��'�.g{N*�w�L����b�$�z��5�p���5-,�S��';�J��8����u�Sk��Cu�Xu�E�v���S�ޣ�E���D��O`��yE
9v��ڱX#�@rºe� J�4$� u*K�ӂx����v����|�c�<x���[�'�FQ���e�qt�rHCCJ_����jC�ID��%lYU��f���N���E%0R>A�}A,�w{~4�y�r϶�9	����b:=�V�ba�1��8�; )����G�0tȳk��$]�Ys�ys����.#���ѬqO$C��о���YA�t��8J�uhS/��lzty۶�~�ִ��H)�R ���Cg|��x������HPX.�`�a\��s����L&!p��ez�'h$U ��e+� �*��Ciuڨ�p(uǪs�%h&�$b$�0�6�7ZB��&��	�����L#V����
:�8�͊5�(`v�;F���y#����T�r���&�b텹��ť��@��;Y�9DUȞ-�>�d����r ��46����wPRCEe.���a�,ө�k׀�hm���o�m��@������C�F�a�X�$�� �F��6�D��h����G��XA�+����4 ����H�4�Xm �f���xL��F:�T���J:X�_#`��P9�ʮJ3lS�!��bU$�n�*h���YУ��8��35Ʈ�S3��+�%;Oah��N������P�\�kɩ*��AQ��۸Vh����L�S�9l���_��E!;��i!^@]Rp��`P����}�z7���~_(@�*$�:�7�Ԉ�}��(�ô�9�z�TMk(��c]6ƭ��HЂ�[�=�|펵�ڪG�em�8W�]⛾nn���8��a�o��B��X2%Z�"$sWV{��y���� �% Ɇ����"���I�5�~���G<q�Q��<�yg�y�ͭ��Y��O�:�ɹ�#8�{�c9��8�
��9�s�'�SӄP�	��OG�骗��v�
�zSkV�R����Ҩ�^�De$���Z'�µ(��*��,$�l>�󀾵5�5�CM���+����x�ř���b���V P�F�.��(�TOF���`c��!Np�3oB�F�EL�����P��OV�r7x6� 0��I��v��-=���؋~"��\I�w�|_t��pBd�و��*�"��䌽��4=��K�	C�	��i7:�GOė"�0��)u2Q��(�H� w���/���9\u.��H��!����REQ����>�-�|�"�������^�p<��h��O�;��]gj�-_5�����V)1�TJ���LC�=É����s^�_��tN��������������s����F���u��[}�PBH��Z"c~�n  /}�j�W���N��~�m��m���n�s����/^���70���j�x�N�� <�Mi��'.$U��ܘ#)g50��h��G��n�\[Є�	yM�(	��di�j�^�A��9��55�9�ݧ�����ힱ=��t��S�B鰾��ޭ1t*T`Z�z��a��58b籧L�x	���0��o��/��oӢ�b�t6�K����rlJG	�X�����&$%���07���F���'�h�}�N��M�����[�)��dw�~�ֲ⃭�ihD�[P�wU?�i�J��w��X��X��P�wN&�~�kW�Q|���_����k�tk�,=���_��l�v��R;���_R���x�__�����h����C���Ml��a�ʀA����M�<����z�5��R�����wy���q���ܽs%�?�6��e,����f�^�VF�QX�ʾ�:Pľ��&*�>p��8?:8��	AnM�c������&�kJV�|t��E�i���O�/g �jռ Y�K��q�A0
��zӀ�Z�Z��h�6Ì�~�������]��� �  0��_��J���T��)2�R��/r;��#e����R�����"�~i�ٚyʜZ������^ؐ�Ođ��eΏ���ha��͏2����31�[���K�-F|���H��_�HlwxW�0�9��=��䲬7@=?�W���?]�����6(7�D��"�9��o��ƻ�4!{񘞍:��>��ѫȥ��k�אU�S�:��(�a��3�8g_HL�Y[~�NZ����`V��ꇪ�t��R	�W�����x��9uVHqt����H�@�(ԕ���{�Cg�����`�Ǎ�G��)�
eH� ڐ+�qj@�b~��+�E��UI�~�~im��G���g�	��~Wr�PQ�f��s*��ckR3�����_���dP�/�D�w!�&�G({��Ʋ�k�<�PY��߶ɋ��������n4߻IO��� .>�ݽ&�֢|l_�邜�3��ٞnX�Ӱ�{{��_U��^����w��O^�x�k:�      �   �  x�e�ݎ�0���S���(`���j�v+��ݢ�ZE�(a�[bP����w��.AH�<�3�81D3���o���;6��צ�Ư�c�N�������|(������<ȋk����?��j{�q{+²>l�{U���a�-doz���#+���Ȳ0	���q0~s���&���YZ��&LSKz��xK�|��B�۞�����o�c�:��gz�!�"����0�H�hs���ZP9����_qx5e���������@+�H�}��W�*Z��ti�f"�@f@v�3��װ�J�	w���Q5vBQ]�*�BP�R  �R-'rRF:l	!4X��$p�%��½��f�����x��b.
�/��k0kq㘋3s�a�\6}��Z֜�W8Z�V�W����L��W�Epv��p�J��;D7\�If���<��+     